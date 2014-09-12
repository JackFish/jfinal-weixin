/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.message.out;

/**
	回复图片消息
	<xml>
		<ToUserName><![CDATA[toUser]]></ToUserName>
		<FromUserName><![CDATA[fromUser]]></FromUserName>
		<CreateTime>12345678</CreateTime>
		<MsgType><![CDATA[image]]></MsgType>
			<Image>
				<MediaId><![CDATA[media_id]]></MediaId>
			</Image>
	</xml>
 */
public class OutImageMessage extends OutMessage {
	public static final String TEMPLATE = 
			"<xml>\n" +
			"<ToUserName><![CDATA[${toUser}]]></ToUserName>\n" +
			"<FromUserName><![CDATA[${fromUser}]]></FromUserName>\n" +
			"<CreateTime>${createTime}</CreateTime>\n" +
			"<MsgType><![CDATA[${msgType}]]></MsgType>\n" +
				"<Image>\n" +
					"<MediaId><![CDATA[${mediaId}]]></MediaId>\n" +
				"</Image>\n" +
			"</xml>";
	
	private String mediaId;
	
	public OutImageMessage() {
		this.msgType = "image";
	}
	
	public String getMediaId() {
		return mediaId;
	}
	
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
}



