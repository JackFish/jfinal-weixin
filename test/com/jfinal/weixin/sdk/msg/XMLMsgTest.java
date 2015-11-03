package com.jfinal.weixin.sdk.msg;

import org.junit.Assert;
import org.junit.Test;

import com.jfinal.weixin.sdk.msg.in.InMsg;
import com.jfinal.weixin.sdk.msg.in.event.InMenuEvent;
import com.jfinal.weixin.sdk.msg.in.event.ScanCodeInfo;

public class XMLMsgTest {
	
	@Test
	public void test001() {
		String xml = "<xml>"
				+ "<ToUserName><![CDATA[ToUserName]]></ToUserName>"
				+ "<FromUserName><![CDATA[FromUserName]]></FromUserName>"
				+ "<CreateTime>1446526359</CreateTime>"
				+ "<MsgType><![CDATA[event]]></MsgType>"
				+ "<Event><![CDATA[scancode_waitmsg]]></Event>"
				+ "<EventKey><![CDATA[2_1]]></EventKey>"
				+ "<ScanCodeInfo>"
					+ "<ScanType><![CDATA[qrcode]]></ScanType>"
					+ "<ScanResult><![CDATA[http://www.jfinal.com]]></ScanResult>"
				+ "</ScanCodeInfo>"
				+ "</xml>";

		InMsg inMsg = InMsgParser.parse(xml);
		
		Assert.assertTrue(inMsg instanceof InMenuEvent);
		InMenuEvent inMenuEvent = (InMenuEvent) inMsg;

		ScanCodeInfo scanCodeInfo = inMenuEvent.getScanCodeInfo();
		String scanType = scanCodeInfo.getScanType();
		String scanResult = scanCodeInfo.getScanResult();

		Assert.assertEquals(scanType, "qrcode");
		Assert.assertEquals(scanResult, "http://www.jfinal.com");
	}
	
}
