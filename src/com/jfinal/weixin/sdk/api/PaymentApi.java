package com.jfinal.weixin.sdk.api;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.jfinal.kit.HttpKit;
import com.jfinal.weixin.sdk.kit.PaymentKit;

/**
 * 微信支付api
 * @author L.cm
 */
public class PaymentApi {
	
	private static String unifiedOrderUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	
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
		return HttpKit.post(unifiedOrderUrl, PaymentKit.toXml(params));
	}
	
	private static String orderQueryUrl = "https://api.mch.weixin.qq.com/pay/orderquery";
	
//	公众账号ID	appid	是	String(32)	wxd678efh567hg6787	微信分配的公众账号ID（企业号corpid即为此appId）
//	商户号	mch_id	是	String(32)	1230000109	微信支付分配的商户号
//	微信订单号	transaction_id	二选一	String(32)	1009660380201506130728806387	微信的订单号，优先使用
//	商户订单号	out_trade_no	String(32)	20150806125346	商户系统内部的订单号，当没提供transaction_id时需要传这个。
//	随机字符串	nonce_str	是	String(32)	C380BEC2BFD727A4B6845133519F3AD6	随机字符串，不长于32位。推荐随机数生成算法
//	签名	sign	是	String(32)	5K8264ILTKCH16CQ2502SI8ZNMTM67VS
	
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	private static Map<String, String> orderquery(Map<String, String> params) {
		params.put("nonce_str", getUUID());
		String xmlStr = HttpKit.post(orderQueryUrl, PaymentKit.toXml(params));
		return PaymentKit.xmlToMap(xmlStr);
	}
	
	public static Map<String, String> queryByTransactionId(String appid, String mch_id, String paternerKey, String transaction_id) {
		Map<String, String> params = new HashMap<String, String>();
		return params;
	}
	
	public static Map<String, String> queryByOutTradeNo(String appid, String mch_id, String paternerKey, String out_trade_no) {
		Map<String, String> params = new HashMap<String, String>();
		return params;
	}
}
