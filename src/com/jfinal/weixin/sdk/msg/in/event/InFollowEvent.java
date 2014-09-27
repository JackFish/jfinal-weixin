/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.msg.in.event;

import com.jfinal.weixin.sdk.msg.in.InMsg;

/**
	接收 关注/取消关注事件
	<xml>
		<ToUserName><![CDATA[toUser]]></ToUserName>
		<FromUserName><![CDATA[FromUser]]></FromUserName>
		<CreateTime>123456789</CreateTime>
		<MsgType><![CDATA[event]]></MsgType>
			<Event><![CDATA[subscribe]]></Event>
	</xml>
*/

/**
	关注实测数据结果： 比官方文档多出一个 EventKey 标记
	<xml>
		<ToUserName><![CDATA[gh_e21b62f685f4]]></ToUserName>
		<FromUserName><![CDATA[o5Ljujn78A_S0uk_WvAM_fKFEXm4]]></FromUserName>
		<CreateTime>1411785252</CreateTime>
		<MsgType><![CDATA[event]]></MsgType>
			<Event><![CDATA[subscribe]]></Event>
			<EventKey><![CDATA[]]></EventKey>
	</xml>
	取消关注实测数据结果：比官方文档多出一个 EventKey 标记
	<xml>
		<ToUserName><![CDATA[gh_e21b62f685f4]]></ToUserName>
		<FromUserName><![CDATA[o5Ljujn78A_S0uk_WvAM_fKFEXm4]]></FromUserName>
		<CreateTime>1411785559</CreateTime>
		<MsgType><![CDATA[event]]></MsgType>
			<Event><![CDATA[unsubscribe]]></Event>
			<EventKey><![CDATA[]]></EventKey>
	</xml>
*/
public class InFollowEvent extends InMsg {
	
	// 订阅：subscribe
	// 取消订阅：unsubscribe
	private String event;
	
	public InFollowEvent(String toUserName, String fromUserName, Integer createTime, String msgType) {
		super(toUserName, fromUserName, createTime, msgType);
	}
	
	public String getEvent() {
		return event;
	}
	
	public void setEvent(String event) {
		this.event = event;
	}
}






