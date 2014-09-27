/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.msg.out;

import com.jfinal.weixin.sdk.msg.in.InMsg;

/**
	回复音乐消息
	<xml>
		<ToUserName><![CDATA[toUser]]></ToUserName>
		<FromUserName><![CDATA[fromUser]]></FromUserName>
		<CreateTime>12345678</CreateTime>
		<MsgType><![CDATA[music]]></MsgType>
			<Music>
				<Title><![CDATA[TITLE]]></Title>
				<Description><![CDATA[DESCRIPTION]]></Description>
				<MusicUrl><![CDATA[MUSIC_Url]]></MusicUrl>
				<HQMusicUrl><![CDATA[HQ_MUSIC_Url]]></HQMusicUrl>
				<ThumbMediaId><![CDATA[media_id]]></ThumbMediaId>
			</Music>
	</xml>
*/
public class OutMusicMsg extends OutMsg {
	
	public static final String TEMPLATE =
		"<xml>\n" +
			"<ToUserName><![CDATA[${__msg.toUserName}]]></ToUserName>\n" +
			"<FromUserName><![CDATA[${__msg.fromUserName}]]></FromUserName>\n" +
			"<CreateTime>${__msg.createTime}</CreateTime>\n" +
			"<MsgType><![CDATA[${__msg.msgType}]]></MsgType>\n" +
				"<Music>\n" +
					"<Title><![CDATA[${(__msg.title)!}]]></Title>\n" +
					"<Description><![CDATA[${(__msg.description)!}]]></Description>\n" +
					"<MusicUrl><![CDATA[${(__msg.musicUrl)!}]]></MusicUrl>\n" +
					"<HQMusicUrl><![CDATA[${(__msg.hqMusicUrl)!}]]></HQMusicUrl>\n" +
					// 官司方文档错误，无此标记: "<ThumbMediaId><![CDATA[${__msg.thumbMediaId}]]></ThumbMediaId>\n" +
					"<FuncFlag>${__msg.funcFlag}</FuncFlag>\n" +
				"</Music>\n" +
		"</xml>";
			
	private String title;		// 不是必须
	private String description;	// 不是必须
	private String musicUrl;	// 不是必须
	private String hqMusicUrl;	// 不是必须
	// private String thumbMediaId;	// 官方文档有误，无此属性
	private String funcFlag = "0";
	
	public OutMusicMsg() {
		this.msgType = "music";
	}
	
	public OutMusicMsg(InMsg inMsg) {
		super(inMsg);
		this.msgType = "music";
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
	
	public String getMusicUrl() {
		return musicUrl;
	}
	
	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}
	
	public String getHqMusicUrl() {
		return hqMusicUrl;
	}
	
	public void setHqMusicUrl(String hqMusicUrl) {
		this.hqMusicUrl = hqMusicUrl;
	}
	
	public String getFuncFlag() {
		return funcFlag;
	}
	
	// 设置为星标
	public void setFuncFlag(boolean funcFlag) {
		this.funcFlag = funcFlag ? "1" : "0";
	}
	
	/* 官方文档有误，无此属性
	public String getThumbMediaId() {
		return thumbMediaId;
	}
	
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}*/
}






