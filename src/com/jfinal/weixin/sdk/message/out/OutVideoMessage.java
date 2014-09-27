/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.message.out;

import com.jfinal.weixin.sdk.message.in.InMessage;

/**
	回复视频消息
	<xml>
		<ToUserName><![CDATA[toUser]]></ToUserName>
		<FromUserName><![CDATA[fromUser]]></FromUserName>
		<CreateTime>12345678</CreateTime>
		<MsgType><![CDATA[video]]></MsgType>
			<Video>
				<MediaId><![CDATA[media_id]]></MediaId>
				<Title><![CDATA[title]]></Title>
				<Description><![CDATA[description]]></Description>
			</Video>
	</xml>
 */
public class OutVideoMessage extends OutMessage {
	public static final String TEMPLATE =
			"<xml>\n" +
			"<ToUserName><![CDATA[${__msg.toUserName}]]></ToUserName>\n" +
			"<FromUserName><![CDATA[${__msg.fromUserName}]]></FromUserName>\n" +
			"<CreateTime>${__msg.createTime}</CreateTime>\n" +
			"<MsgType><![CDATA[${__msg.msgType}]]></MsgType>\n" +
				"<Video>\n" +
					"<MediaId><![CDATA[${__msg.mediaId}]]></MediaId>\n" +
					"<Title><![CDATA[${__msg.title}]]></Title>\n" +
					"<Description><![CDATA[${__msg.description}]]></Description>\n" +
				"</Video>\n" +
			"</xml>";
	
	private String mediaId;
	private String title;
	private String description;
	
	public OutVideoMessage() {
		this.msgType = "video";
	}
	
	public OutVideoMessage(InMessage inMessage) {
		super(inMessage);
		this.msgType = "video";
	}
	
	public String getMediaId() {
		return mediaId;
	}
	
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}







