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
	//商户相关资料 
	static String appid = "";
	static String partner = "";
	static String paternerKey = "";
	
	public static void testCreate() throws DocumentException {
		//商户相关资料 
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
	
	public static void testQuery() {
		Map<String, String> map1 = PaymentApi.queryByOutTradeNo(appid, partner, paternerKey, "US2015111300040");
		System.out.println(map1);
		// {transaction_id=1004240183201511131584295448, nonce_str=ZY1cxjlrMH72E6kN, trade_state=SUCCESS, bank_type=CFT, openid=o7FXhsqNPNVTA0MyXv17mad-OWCE, sign=5FB74F13A13834764537EF1219F30772, return_msg=OK, fee_type=CNY, mch_id=1260676301, cash_fee=1, out_trade_no=US2015111300041, appid=wx316b370c618de94d, total_fee=1, trade_type=JSAPI, result_code=SUCCESS, attach=, time_end=20151113143319, is_subscribe=Y, return_code=SUCCESS}

//		Map<String, String> map2 = PaymentApi.queryByTransactionId(appid, partner, paternerKey, "1004240183201511131584295448");
//		System.out.println(map2);
		//{transaction_id=1004240183201511131584295448, nonce_str=HmkxvZXWMU9MZWcM, trade_state=SUCCESS, bank_type=CFT, openid=o7FXhsqNPNVTA0MyXv17mad-OWCE, sign=0C43E2A5097DB8D5ABE0F1171301C56D, return_msg=OK, fee_type=CNY, mch_id=1260676301, cash_fee=1, out_trade_no=US2015111300041, appid=wx316b370c618de94d, total_fee=1, trade_type=JSAPI, result_code=SUCCESS, attach=, time_end=20151113143319, is_subscribe=Y, return_code=SUCCESS}
	}
	
	public static void testClose() {
		Map<String, String> map1 = PaymentApi.closeOrder(appid, partner, paternerKey, "US2015111300030");
		System.out.println(map1);
//		{nonce_str=G50b3BEKUotSOnNV, appid=wx316b370c618de94d, sign=0F3B33FA5B7B8F7338849BF4E84FAF48, return_msg=OK, result_code=SUCCESS, mch_id=1260676301, sub_mch_id=, return_code=SUCCESS}
	}
	
	
	/**
	 * 退款需要使用证书：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_3
	 * @throws Exception 
	 */
	public static void testRefund() {
//		公众账号ID	appid	是	String(32)	wx8888888888888888	微信分配的公众账号ID（企业号corpid即为此appId）
//		商户号		mch_id	是	String(32)	1900000109	微信支付分配的商户号
//		设备号		device_info	否	String(32)	013467007045764	终端设备号
//		随机字符串	nonce_str	是	String(32)	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	随机字符串，不长于32位。推荐随机数生成算法
//		签名			sign	是	String(32)	C380BEC2BFD727A4B6845133519F3AD6	签名，详见签名生成算法
//		微信订单号	transaction_id	二选一	String(28)	1217752501201407033233368018	微信生成的订单号，在支付通知中有返回
//		商户订单号	out_trade_no	String(32)	1217752501201407033233368018	商户侧传给微信的订单号
//		商户退款单号	out_refund_no	是	String(32)	1217752501201407033233368018	商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔
//		总金额		total_fee	是	Int	100	订单总金额，单位为分，只能为整数，详见支付金额
//		退款金额		refund_fee	是	Int	100	退款总金额，订单总金额，单位为分，只能为整数，详见支付金额
//		货币种类		refund_fee_type	否	String(8)	CNY	货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
//		操作员		op_user_id	是	String(32)	1900000109	操作员帐号, 默认为商户号
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", appid);
		params.put("mch_id", partner);
		params.put("out_trade_no", "US2015111300044");
		params.put("out_refund_no", System.currentTimeMillis() + "");
		params.put("total_fee", "1");
		params.put("refund_fee", "1");
		params.put("op_user_id", partner);
		
		String certPath = "/Users/lcm/Desktop/apiclient_cert.p12";
		
		// 申请退款，内部添加了随机字符串nonce_str和签名sign
		Map<String, String> map1 = PaymentApi.refund(params, paternerKey, certPath);
		System.out.println(map1);
	}
	
	public static void main(String[] args) {
//		testQuery();
//		testClose();
		testRefund();
	}
	
}