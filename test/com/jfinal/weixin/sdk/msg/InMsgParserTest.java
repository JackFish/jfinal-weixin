package com.jfinal.weixin.sdk.msg;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class InMsgParserTest {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws DocumentException {
		String xml = 
			"<xml>\n" +
				"<ToUserName><![CDATA[James]]></ToUserName>\n" +
				"<FromUserName><![CDATA[JFinal]]></FromUserName>\n" +
				"<CreateTime>1348831860</CreateTime>\n" +
				"<MsgType><![CDATA[text]]></MsgType>\n" +
					"<Content><![CDATA[this is a test]]></Content>\n" +
					"<MsgId>1234567890123456</MsgId>\n" +
			"</xml>";
		
//		InTextMsg msg = (InTextMsg)parse(xml);
//		System.out.println(msg.getToUserName());
//		System.out.println(msg.getFromUserName());
//		System.out.println(msg.getContent());
		
		
		String xml_2 = 
				"<xml>\n" +
					"<ToUserName><![CDATA[James]]></ToUserName>\n" +
					"<FromUserName><![CDATA[JFinal]]></FromUserName>\n" +
					"<CreateTime>1348831860</CreateTime>\n" +
					"<MsgType><![CDATA[text]]></MsgType>\n" +
						"<Content><![CDATA[this is a test]]></Content>\n" +
						"<MsgId>1234567890123456</MsgId>\n" +
				"</xml>";
		
		Document doc = DocumentHelper.parseText(xml_2);
		Element root = doc.getRootElement();
		String value = root.elementText("abc");
		System.out.println(value);
	}
}
