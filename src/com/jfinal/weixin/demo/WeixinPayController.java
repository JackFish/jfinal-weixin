package com.jfinal.weixin.demo;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.jfinal.core.Controller;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.StrKit;
import com.jfinal.weixin.sdk.api.PaymentApi;
import com.jfinal.weixin.sdk.api.PaymentApi.TradeType;
import com.jfinal.weixin.sdk.kit.IpKit;
import com.jfinal.weixin.sdk.kit.PaymentKit;
import com.jfinal.weixin.sdk.utils.JsonUtils;

/**
 * 感谢 *半杯* 童鞋联调支付API
 * @author L.cm
 */
public class WeixinPayController extends Controller {
	//商户相关资料
	private static String appid = "";
	private static String partner = "";
	private static String paternerKey = "";
	private static String notify_url = "http://www.xxx.com/pay/pay_notify";
	
	public void index() throws DocumentException {
		// openId，采用 网页授权获取 access_token API：SnsAccessTokenApi获取
		String openId = "";

		// 统一下单文档地址：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", appid);
		params.put("mch_id", partner);
		params.put("body", "JFinal2.0极速开发");
		params.put("out_trade_no", "977773682111");
		params.put("total_fee", "1");
		
		String ip = IpKit.getRealIp(getRequest());
		if (StrKit.isBlank(ip)) {
			ip = "127.0.0.1";
		}
		
		params.put("spbill_create_ip", ip);
		params.put("trade_type", TradeType.JSAPI.name());
		params.put("nonce_str", System.currentTimeMillis() / 1000 + "");
		params.put("notify_url", notify_url);
		params.put("openid", openId);

		String sign = PaymentKit.createSign(params, paternerKey);
		params.put("sign", sign);
		String xmlResult = PaymentApi.pushOrder(params);
		
		System.out.println(xmlResult);
		
		Document doc = DocumentHelper.parseText(xmlResult);
		Element root = doc.getRootElement();
		String return_code = root.elementText("return_code");
		String result_code = root.elementText("result_code");
		
		// 
		
		String return_msg = root.elementText("return_msg");
		String prepay_id = root.elementText("prepay_id");
		
		Map<String, String> packageParams = new HashMap<String, String>();
		packageParams.put("appId", appid);
		packageParams.put("timeStamp", System.currentTimeMillis() / 1000 + "");
		packageParams.put("nonceStr", System.currentTimeMillis() + "");
		packageParams.put("package", "prepay_id=" + prepay_id);
		packageParams.put("signType", "MD5");
		String packageSign = PaymentKit.createSign(packageParams, paternerKey);
		packageParams.put("paySign", packageSign);
		
		String jsonStr = JsonUtils.toJson(packageParams);
		setAttr("json", jsonStr);
		System.out.println(jsonStr);
		render("/jsp/pay.jsp");
	}
	
	public void pay_notify() throws DocumentException {
		// 支付结果通用通知文档: https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_7
		
		String xmlMsg = HttpKit.readIncommingRequestData(getRequest());
		System.out.println("支付通知="+xmlMsg);
		Map<String, String> params = PaymentKit.xmlToMap(xmlMsg);
		
		String result_code  = params.get("result_code");
		String totalFee     = params.get("total_fee");
		String orderId      = params.get("out_trade_no");
		String transId      = params.get("transaction_id");
		String timeEnd      = params.get("time_end");
		
		if(PaymentKit.verifyNotify(params, paternerKey)){
			if (("SUCCESS").equals(result_code)) {
				//更新订单信息
				System.out.println("更新订单信息");
				
				Map<String, String> xml = new HashMap<String, String>();
				xml.put("return_code", "SUCCESS");
				xml.put("return_msg", "OK");
				renderText(PaymentKit.toXml(xml));
				return;
			}
		}
		renderText("");
	}
}
