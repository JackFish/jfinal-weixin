package com.jfinal.weixin.sdk.api;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.weixin.sdk.api.PaymentApi.TradeType;
import com.jfinal.weixin.sdk.kit.PaymentKit;

public class PaymentApiTest {

	public static void main(String[] args) {
		//商户相关资料 
		String appid = "";
		String partner = "";
		String paternerKey = "";
		String openId = "";
		String notify_url = "";

		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", appid);
		params.put("mch_id", partner);
		params.put("notify_url", notify_url);
		params.put("openid", openId);

		params.put("body", "测试");
		params.put("out_trade_no", "9777736");
		params.put("total_fee", "1");
		params.put("spbill_create_ip", "127.0.0.1");
		params.put("nonce_str", System.currentTimeMillis() / 1000 + "");
		params.put("trade_type", TradeType.JSAPI.name());

		String sign = PaymentKit.createSign(params, paternerKey);
		params.put("sign", sign);
		System.out.println(PaymentApi.pushOrder(params));
	}
}
