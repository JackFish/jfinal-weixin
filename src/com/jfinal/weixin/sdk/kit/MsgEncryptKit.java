package com.jfinal.weixin.sdk.kit;

import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.encrypt.WXBizMsgCrypt;

/**
 * 对微信平台官方给出的加密解析代码进行再次封装
 * 
 * 异常java.security.InvalidKeyException:illegal Key Size的解决方案：
 * 1：在官方网站下载JCE无限制权限策略文件（JDK7的下载地址：
 *     http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html
 * 2：下载后解压，可以看到local_policy.jar和US_export_policy.jar以及readme.txt
 * 3：如果安装了JRE，将两个jar文件放到%JRE_HOME%\lib\security目录下覆盖原来的文件
 * 4：如果安装了JDK，将两个jar文件放到%JDK_HOME%\jre\lib\security目录下覆盖原来文件
 * 
 * 
 * 设置为消息加密模式后 JFinal Action Report 中有如下参数：
 * timestamp=1417610658
 * encrypt_type=aes
 * nonce=132155339
 * msg_signature=8ed2a14146c924153743162ab2c0d28eaf30a323
 * signature=1a3fad9a528869b1a73faf4c8054b7eeda2463d3
 */
public class MsgEncryptKit {
	
	private static final String format = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[%1$s]]></Encrypt></xml>";
	
	public static String encrypt(String msg, String timestamp, String nonce) {
		try {
			ApiConfig ac = ApiConfigKit.getApiConfig();
			WXBizMsgCrypt pc = new WXBizMsgCrypt(ac.getToken(), ac.getEncodingAesKey(), ac.getAppId());
			return pc.encryptMsg(msg, timestamp, nonce);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String decrypt(String encryptedMsg, String timestamp, String nonce, String msgSignature) {
		try {
			ApiConfig ac = ApiConfigKit.getApiConfig();
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			StringReader sr = new StringReader(encryptedMsg);
			InputSource is = new InputSource(sr);
			Document document = db.parse(is);
			
			Element root = document.getDocumentElement();
			NodeList nodelist1 = root.getElementsByTagName("Encrypt");
			// NodeList nodelist2 = root.getElementsByTagName("MsgSignature");
			
			String encrypt = nodelist1.item(0).getTextContent();
			// String msgSignature = nodelist2.item(0).getTextContent();
			
			String fromXML = String.format(format, encrypt);
			
			String encodingAesKey = ac.getEncodingAesKey();
			if (encodingAesKey == null)
				throw new IllegalStateException("encodingAesKey can not be null, config encodingAesKey first.");
			
			WXBizMsgCrypt pc = new WXBizMsgCrypt(ac.getToken(), encodingAesKey, ac.getAppId());
			return pc.decryptMsg(msgSignature, timestamp, nonce, fromXML);	// 此处 timestamp 如果与加密前的不同则报签名不正确的异常
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}



