package com.jfinal.weixin.sdk.api;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.jfinal.weixin.sdk.api.PaymentApi.TradeType;
import com.jfinal.weixin.sdk.kit.PaymentKit;
import com.jfinal.weixin.sdk.utils.JsonUtils;

public class PaymentApiTest {
	
	public static void main(String[] args) throws DocumentException {
		//商户相关资料 
		String appid = "";
		String partner = "";
		String paternerKey = "";
		String openId = "";
		String notify_url = "";

		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", appid);
		params.put("mch_id", partner);
		params.put("body", "JFinal2.0技术开发");
		params.put("out_trade_no", "97777368");
		params.put("total_fee", "1");
		params.put("spbill_create_ip", "60.12.33.121");
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
		System.out.println(jsonStr);
	}
	
}