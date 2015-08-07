package com.jfinal.weixin.sdk.msg;

import com.jfinal.weixin.sdk.msg.out.OutTextMsg;

public class OutMsgXmlBuilderTest {

	public static void main(String[] args) {
		OutTextMsg msg = new OutTextMsg();
		msg.setToUserName("to james");
		msg.setFromUserName("from james");
		msg.setCreateTime(msg.now());
		msg.setContent("jfinal weixin 极速开发平台碉堡了");
		String xml = OutMsgXmlBuilder.build(msg);
		System.out.println(xml);
	}
}
