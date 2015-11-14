package com.jfinal.weixin.sdk.kit;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.jfinal.kit.HashKit;
import com.jfinal.kit.StrKit;
import com.jfinal.weixin.sdk.utils.IOUtils;

/**
 * 微信支付的统一下单工具类
 * @author L.cm
 */
public class PaymentKit {
	
	private static final String CHARSET = "UTF-8";
	
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * 组装签名的字段
	 * @param params 参数
	 * @param urlEncoder 是否urlEncoder
	 * @return String
	 */
	public static String packageSign(Map<String, String> params, boolean urlEncoder) {
		// 先将参数以其参数名的字典序升序进行排序
		TreeMap<String, String> sortedParams = new TreeMap<String, String>(params);
		// 去除参与的参数sign
		sortedParams.remove("sign");
		// 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (Entry<String, String> param : sortedParams.entrySet()) {
			String value = param.getValue();
			if (StrKit.isBlank(value)) {
				continue;
			}
			if (first) {
				first = false;
			} else {
				sb.append("&");
			}
			sb.append(param.getKey()).append("=");
			if (urlEncoder) {
				try { value = urlEncode(value); } catch (UnsupportedEncodingException e) {}
			}
			sb.append(value);
		}
		return sb.toString();
	}
	
	/**
	 * urlEncode
	 * @param src 微信参数
	 * @return String
	 * @throws UnsupportedEncodingException
	 */
	private static String urlEncode(String src) throws UnsupportedEncodingException {
		return URLEncoder.encode(src, CHARSET).replace("+", "%20");
	}
	
	/**
	 * 生成签名
	 * @return
	 */
	public static String createSign(Map<String, String> params, String paternerKey) {
		String stringA = packageSign(params, false);
		String stringSignTemp = stringA + "&key=" + paternerKey;
		return HashKit.md5(stringSignTemp).toUpperCase();
	}
	
	/**
	 * 支付异步通知时校验sign
	 * @param params
	 * @param paternerKey
	 * @return
	 */
	public static boolean verifyNotify(Map<String, String> params, String paternerKey){
		String sign = PaymentKit.createSign(params, paternerKey);
		return sign.equals(params.get("sign"));
	}
	
	/**
	 * 微信下单，map to xml
	 * @param params 参数
	 * @return String
	 */
	public static String toXml(Map<String, String> params) {
		StringBuilder xml = new StringBuilder();
		xml.append("<xml>");
		for (Entry<String, String> entry : params.entrySet()) {
			String key   = entry.getKey();
			String value = entry.getValue();
			// 略过空值
			if (StrKit.isBlank(value)) continue;
			xml.append("<").append(key).append(">");
				xml.append(entry.getValue());
			xml.append("</").append(key).append(">");
		}
		xml.append("</xml>");
		return xml.toString();
	}
	
	/**
	 * 针对支付的xml，没有嵌套节点的简单处理
	 * @param xml xml字符串
	 * @return Map<String, String> map集合
	 * @throws DocumentException 
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> xmlToMap(String xmlStr) {
		Document doc;
		try {
			doc = DocumentHelper.parseText(xmlStr);
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
		Element root = doc.getRootElement();
		
		List<Element> rList = root.elements();
		Map<String, String> params = new HashMap<String, String>();
		for(Element element :rList){
			params.put(element.getName(), element.getText());
		}
		return params;
	}
	
	/**
	 * 涉及资金回滚的接口会使用到商户证书，包括退款、撤销接口的请求
	 * @param url 请求的地址
	 * @param data xml数据
	 * @param certPath 证书文件目录
	 * @param certPass 证书密码
	 * @return String 回调的xml信息
	 */
	public static String postSSL(String url, String data, String certPath, String certPass) {
		HttpsURLConnection conn = null;
		OutputStream out = null;
		InputStream inputStream = null;
		BufferedReader reader = null;
		try {
			KeyStore clientStore = KeyStore.getInstance("PKCS12");
			clientStore.load(new FileInputStream(certPath), certPass.toCharArray());
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			kmf.init(clientStore, certPass.toCharArray());
			KeyManager[] kms = kmf.getKeyManagers();
			SSLContext sslContext = SSLContext.getInstance("TLSv1");
			
			sslContext.init(kms, null, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
			URL _url = new URL(url);
			conn = (HttpsURLConnection) _url.openConnection();
			
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			
			conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36");
			conn.connect();
			
			out = conn.getOutputStream();
			out.write(data.getBytes(CHARSET));
			out.flush();
			
			inputStream = conn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(inputStream, CHARSET));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null){
				sb.append(line).append("\n");
			}
			return sb.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(reader);
			IOUtils.closeQuietly(inputStream);
			if (conn != null) {
				conn.disconnect();
			}
		}
	}
	
}