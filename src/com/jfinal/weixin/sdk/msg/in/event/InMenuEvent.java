/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.msg.in.event;

/**
	自定义菜单事件
	1： 点击菜单拉取消息时的事件推送
	<xml>
		<ToUserName><![CDATA[toUser]]></ToUserName>
		<FromUserName><![CDATA[FromUser]]></FromUserName>
		<CreateTime>123456789</CreateTime>
		<MsgType><![CDATA[event]]></MsgType>
			<Event><![CDATA[CLICK]]></Event>
			<EventKey><![CDATA[EVENTKEY]]></EventKey>
	</xml>
	
	2： 点击菜单跳转链接时的事件推送
	<xml>
		<ToUserName><![CDATA[toUser]]></ToUserName>
		<FromUserName><![CDATA[FromUser]]></FromUserName>
		<CreateTime>123456789</CreateTime>
		<MsgType><![CDATA[event]]></MsgType>
			<Event><![CDATA[VIEW]]></Event>
			<EventKey><![CDATA[www.qq.com]]></EventKey>
	</xml>
 */
public class InMenuEvent extends EventInMsg {
	// 1. 点击菜单拉取消息时的事件推送： CLICK
	public static final String EVENT_INMENU_CLICK = "CLICK";
	// 2. 点击菜单跳转链接时的事件推送： VIEW
	public static final String EVENT_INMENU_VIEW = "VIEW";

	private String eventKey;
	
	public InMenuEvent(String toUserName, String fromUserName, Integer createTime, String msgType,String event) {
		super(toUserName, fromUserName, createTime, msgType,event);
	}

	public String getEventKey() {
		return eventKey;
	}
	
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}
}



