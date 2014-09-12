/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.message.out;

/**
	回复文本消息
	<xml>
		<ToUserName><![CDATA[toUser]]></ToUserName>
		<FromUserName><![CDATA[fromUser]]></FromUserName>
		<CreateTime>12345678</CreateTime>
		<MsgType><![CDATA[text]]></MsgType>
			<Content><![CDATA[你好]]></Content>
	</xml>
 */
public class OutTextMessage extends OutMessage {
	public static final String TEMPLATE =
			"<xml>\n" +
			"<ToUserName><![CDATA[${toUser}]]></ToUserName>\n" +
			"<FromUserName><![CDATA[${fromUser}]]></FromUserName>\n" +
			"<CreateTime>${createTime}</CreateTime>\n" +
			"<MsgType><![CDATA[${msgType}]]></MsgType>\n" +
				"<Content><![CDATA[${content}]]></Content>\n" +
			"</xml>";
	
	private String content;
	
	public OutTextMessage() {
		this.msgType = "text";
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
}


