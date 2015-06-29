/**
 * Copyright (c) 2011-2015, Unas 小强哥 (unas@qq.com).
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.msg.in.event;

/**
 * 在模版消息发送任务完成后，微信服务器会将是否送达成功作为通知，发送到开发者中心中填写的服务器配置地址中
 * 
	<xml>
		<ToUserName><![CDATA[gh_7f083739789a]]></ToUserName>
		<FromUserName><![CDATA[oia2TjuEGTNoeX76QEjQNrcURxG8]]></FromUserName>
		<CreateTime>1395658920</CreateTime>
		<MsgType><![CDATA[event]]></MsgType>
		<Event><![CDATA[TEMPLATESENDJOBFINISH]]></Event>
		<MsgID>200163836</MsgID>
		<Status><![CDATA[success]]></Status>
	</xml>
 */
public class InTemplateMsgEvent extends EventInMsg {
	
	//"success": 成功
	public static final String EVENT_INTEMPLATEMSG_STATUS_SUCCESS = "success";
	//"block": 用户拒绝接收
	public static final String EVENT_INTEMPLATEMSG_STATUS_BLOCK = "block";
    //"failed: system failed": 发送失败（非用户拒绝）
	public static final String EVENT_INTEMPLATEMSG_STATUS_FAILED = "failed: system failed";

	private String msgId;
	
	/**
	 * "success": 成功
	 * "block": 用户拒绝接收
	 * "failed: system failed": 发送失败（非用户拒绝）
	 */
	private String status;
	
	public InTemplateMsgEvent(String toUserName, String fromUserName, Integer createTime, String msgType, String event) {
		super(toUserName, fromUserName, createTime, msgType, event);
	}

	public String getMsgId() {
		return msgId;
	}
	
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
}

