package com.jfinal.weixin.sdk.api;

import java.util.Map;

import com.jfinal.kit.HttpKit;
import com.jfinal.weixin.sdk.kit.PaymentKit;

/**
 * 微信支付api
 * @author L.cm
 */
public class PaymentApi {
	
	private static String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	
	/**
	 * 交易类型枚举
	 * @author L.cm
	 * email: 596392912@qq.com
	 * site:http://www.dreamlu.net
	 * @date 2015年10月27日 下午9:46:27
	 */
	public static enum TradeType {
		JSAPI, NATIVE, APP
	}
	
	/**
	 * 统一下单
	 * @return
	 */
	public static String pushOrder(Map<String, String> params) {
		return HttpKit.post(url, PaymentKit.toXml(params));
	}
	
}
