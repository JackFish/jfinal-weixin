/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.message.out;

import com.jfinal.weixin.sdk.message.in.InMessage;

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
			"<ToUserName><![CDATA[${__msg.toUserName}]]></ToUserName>\n" +
			"<FromUserName><![CDATA[${__msg.fromUserName}]]></FromUserName>\n" +
			"<CreateTime>${__msg.createTime}</CreateTime>\n" +
			"<MsgType><![CDATA[${__msg.msgType}]]></MsgType>\n" +
				"<Content><![CDATA[${__msg.content}]]></Content>\n" +
			"</xml>";
	
	private String content;
	
	public OutTextMessage() {
		this.msgType = "text";
	}
	
	public OutTextMessage(InMessage inMessage) {
		super(inMessage);
		this.msgType = "text";
	}
	
	public String getContent() {
		return content;
	}
	
	public OutTextMessage setContent(String content) {
		this.content = content;
		return this;
	}
}


