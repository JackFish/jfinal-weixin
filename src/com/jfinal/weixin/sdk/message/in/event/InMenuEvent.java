package com.jfinal.weixin.sdk.message.in.event;

import com.jfinal.weixin.sdk.message.in.InMessage;

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
public class InMenuEvent extends InMessage {
	private String event;
	private String eventKey;
	
	public InMenuEvent(String toUserName, String fromUserName, Integer createTime, String msgType) {
		super(toUserName, fromUserName, createTime, msgType);
	}
	
	public String getEvent() {
		return event;
	}
	
	public void setEvent(String event) {
		this.event = event;
	}
	
	public String getEventKey() {
		return eventKey;
	}
	
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}
}



