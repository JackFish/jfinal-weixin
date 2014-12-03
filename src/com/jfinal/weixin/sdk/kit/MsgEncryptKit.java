package com.jfinal.weixin.sdk.kit;

import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.encrypt.WXBizMsgCrypt;

/**
 * 对微信平台官方给出的加密解析代码进行再次封装
 */
public class MsgEncryptKit {
	
	private static String nonce = "jfinal";
	private static final String timestamp = "1509199518";
	private static final String format = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[%1$s]]></Encrypt></xml>";
	
	public static String encrypt(String msg) {
		try {
			WXBizMsgCrypt pc = new WXBizMsgCrypt(ApiConfig.getToken(), ApiConfig.getEncodingAesKey(), ApiConfig.getAppId());
			return pc.encryptMsg(msg, timestamp, nonce);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String decrypt(String encryptedMsg) {
		try {
			WXBizMsgCrypt pc = new WXBizMsgCrypt(ApiConfig.getToken(), ApiConfig.getEncodingAesKey(), ApiConfig.getAppId());
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			StringReader sr = new StringReader(encryptedMsg);
			InputSource is = new InputSource(sr);
			Document document = db.parse(is);
			
			Element root = document.getDocumentElement();
			NodeList nodelist1 = root.getElementsByTagName("Encrypt");
			NodeList nodelist2 = root.getElementsByTagName("MsgSignature");
			
			String encrypt = nodelist1.item(0).getTextContent();
			String msgSignature = nodelist2.item(0).getTextContent();
			
			String fromXML = String.format(format, encrypt);
			
			return pc.decryptMsg(msgSignature, timestamp, nonce, fromXML);	// 此处 timestamp 如果与加密前的不同则报签名不正确的异常
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) {
		ApiConfig.setToken("myToken");
		ApiConfig.setEncodingAesKey("abcdefghijklmnopqrstuvwxyz0123456789ABCDEFG");
		ApiConfig.setAppId("wxb11529c136998cb6");
		String original = "中文<xml><ToUserName><![CDATA[oia2TjjewbmiOUlr6X-1crbLOvLw]]></ToUserName><FromUserName><![CDATA[gh_7f083739789a]]></FromUserName><CreateTime>1407743423</CreateTime><MsgType><![CDATA[video]]></MsgType><Video><MediaId><![CDATA[eYJ1MbwPRJtOvIEabaxHs7TX2D-HV71s79GUxqdUkjm6Gs2Ed1KF3ulAOA9H1xG0]]></MediaId><Title><![CDATA[testCallBackReplyVideo]]></Title><Description><![CDATA[testCallBackReplyVideo]]></Description></Video></xml>";
		
		System.out.println("加密前 -----------");
		System.out.println(original);
		
		System.out.println("\n加密后 -----------");
		String afterEncript = encrypt(original);
		System.out.println(afterEncript);
		
		System.out.println("\n解密后 -----------");
		String afterDecript = decrypt(afterEncript);
		System.out.println(afterDecript);
		
		System.out.println("\n解密后与原文对比 -----------");
		System.out.println(afterDecript.equals(original));
	}
}



