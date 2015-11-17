package com.jfinal.weixin.sdk.api;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.kit.HttpKit;
import com.jfinal.weixin.sdk.kit.PaymentKit;

/**
 * 微信支付api
 * @author L.cm
 */
public class PaymentApi {
	
	private PaymentApi() {}
	
	// 文档地址：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1
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
	
	/**
	 * 支付相关请求
	 */
	private static Map<String, String> request(String url, Map<String, String> params, String paternerKey) {
		params.put("nonce_str", PaymentKit.getUUID());
		String sign = PaymentKit.createSign(params, paternerKey);
		params.put("sign", sign);
		String xmlStr = HttpKit.post(url, PaymentKit.toXml(params));
		return PaymentKit.xmlToMap(xmlStr);
	}
	
	// 文档地址：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_2
	private static String orderQueryUrl = "https://api.mch.weixin.qq.com/pay/orderquery";
	
	/**
	 * 根据商户订单号查询信息
	 * @param appid 公众账号ID
	 * @param mch_id 商户号
	 * @param paternerKey 商户密钥
	 * @param transaction_id 微信订单号
	 * @return Map<String, String> 回调信息
	 */
	public static Map<String, String> queryByTransactionId(String appid, String mch_id, String paternerKey, String transaction_id) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", appid);
		params.put("mch_id", mch_id);
		params.put("transaction_id", transaction_id);
		return request(orderQueryUrl, params, paternerKey);
	}
	
	/**
	 * 根据商户订单号查询信息
	 * @param appid 公众账号ID
	 * @param mch_id 商户号
	 * @param paternerKey 商户密钥
	 * @param out_trade_no 商户订单号
	 * @return Map<String, String> 回调信息
	 */
	public static Map<String, String> queryByOutTradeNo(String appid, String mch_id, String paternerKey, String out_trade_no) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", appid);
		params.put("mch_id", mch_id);
		params.put("out_trade_no", out_trade_no);
		return request(orderQueryUrl, params, paternerKey);
	}
	
	// 文档地址：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_3
	private static String closeOrderUrl = "https://api.mch.weixin.qq.com/pay/closeorder";
	
	/**
	 * 关闭订单
	 * @param appid 公众账号ID
	 * @param mch_id 商户号
	 * @param paternerKey 商户密钥
	 * @param out_trade_no 商户订单号
	 * @return Map<String, String> 回调信息
	 */
	public static Map<String, String> closeOrder(String appid, String mch_id, String paternerKey, String out_trade_no) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", appid);
		params.put("mch_id", mch_id);
		params.put("out_trade_no", out_trade_no);
		return request(closeOrderUrl, params, paternerKey);
	}
	
	// 申请退款文档地址：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4
	public static String refundUrl = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	
	/**
	 * 申请退款，内部添加了随机字符串nonce_str和签名sign
	 * @param params 参数map，内部添加了随机字符串nonce_str和签名sign
	 * @param paternerKey 商户密钥
	 * @param certPath 证书文件目录
	 * @return Map<String, String> map
	 * @throws Exception 
	 */
	public static Map<String, String> refund(Map<String, String> params, String paternerKey, String certPath) {
		params.put("nonce_str", PaymentKit.getUUID());
		String sign = PaymentKit.createSign(params, paternerKey);
		params.put("sign", sign);
		String partner = params.get("mch_id");
		String xmlStr = PaymentKit.postSSL(refundUrl, PaymentKit.toXml(params), certPath, partner);
		return PaymentKit.xmlToMap(xmlStr);
	}
	
	// 查询退款文档地址：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_5
	private static String refundQueryUrl = "https://api.mch.weixin.qq.com/pay/refundquery";
	
	private static Map<String, String> baseRefundQuery(Map<String, String> params, String appid, String mch_id, String paternerKey) {
		params.put("appid", appid);
		params.put("mch_id", mch_id);
		return request(refundQueryUrl, params, paternerKey);
	}
	
	/**
	 * 根据微信订单号查询退款
	 * @param appid 公众账号ID
	 * @param mch_id 商户号
	 * @param paternerKey 商户密钥
	 * @param transaction_id 微信订单号
	 * @return Map<String, String> map
	 */
	public static Map<String, String> refundQueryByTransactionId(String appid, String mch_id, String paternerKey, String transaction_id) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("transaction_id", transaction_id);
		return baseRefundQuery(params, appid, mch_id, paternerKey);
	}
	
	/**
	 * 根据微信订单号查询退款
	 * @param appid 公众账号ID
	 * @param mch_id 商户号
	 * @param paternerKey 商户密钥
	 * @param out_trade_no 商户订单号
	 * @return Map<String, String> map
	 */
	public static Map<String, String> refundQueryByOutTradeNo(String appid, String mch_id, String paternerKey, String out_trade_no) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("out_trade_no", out_trade_no);
		return baseRefundQuery(params, appid, mch_id, paternerKey);
	}
	
	/**
	 * 根据微信订单号查询退款
	 * @param appid 公众账号ID
	 * @param mch_id 商户号
	 * @param paternerKey 商户密钥
	 * @param out_refund_no 商户退款单号
	 * @return Map<String, String> map
	 */
	public static Map<String, String> refundQueryByOutRefundNo(String appid, String mch_id, String paternerKey, String out_refund_no) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("out_refund_no", out_refund_no);
		return baseRefundQuery(params, appid, mch_id, paternerKey);
	}
	
	/**
	 * 根据微信订单号查询退款
	 * @param appid 公众账号ID
	 * @param mch_id 商户号
	 * @param paternerKey 商户密钥
	 * @param refund_id 微信退款单号
	 * @return Map<String, String> map
	 */
	public static Map<String, String> refundQueryByRefundId(String appid, String mch_id, String paternerKey, String refund_id) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("refund_id", refund_id);
		return baseRefundQuery(params, appid, mch_id, paternerKey);
	}
	
}
