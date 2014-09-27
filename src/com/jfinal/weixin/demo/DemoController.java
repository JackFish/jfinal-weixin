/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.demo;

import com.jfinal.weixin.sdk.jfinal.WeixinController;
import com.jfinal.weixin.sdk.message.in.InImageMessage;
import com.jfinal.weixin.sdk.message.in.InLinkMessage;
import com.jfinal.weixin.sdk.message.in.InLocationMessage;
import com.jfinal.weixin.sdk.message.in.InMessage;
import com.jfinal.weixin.sdk.message.in.InTextMessage;
import com.jfinal.weixin.sdk.message.in.InVideoMessage;
import com.jfinal.weixin.sdk.message.in.InVoiceMessage;
import com.jfinal.weixin.sdk.message.in.event.InFollowEvent;
import com.jfinal.weixin.sdk.message.in.event.InLocationEvent;
import com.jfinal.weixin.sdk.message.in.event.InMenuEvent;
import com.jfinal.weixin.sdk.message.in.event.InQrCodeEvent;
import com.jfinal.weixin.sdk.message.in.speech_recognition.InSpeechRecognitionResults;
import com.jfinal.weixin.sdk.message.out.OutMessage;
import com.jfinal.weixin.sdk.message.out.OutTextMessage;

/**
 * 设置好 url 与 token 即可开始开发
 */
public class DemoController extends WeixinController {
	
	public void index() {
		System.out.println(getInMessageXml());
		super.index();
		// String inMessageXml = getInMessageXml();
		// 可将 inMessageXml 写入数据库以便查看
		// new InMessave(inMessageXml).save();
	}
	
	private void initTestOutTextMessage(OutMessage outMsg, InMessage inMsg) {
		outMsg.setToUserName(inMsg.getFromUserName());
		outMsg.setFromUserName(inMsg.getToUserName());
		outMsg.setCreateTime(outMsg.now());
		outMsg.setMsgType("text");
		render(outMsg);
	}
	
	protected void processInTextMessage(InTextMessage inTextMessage) {
		OutTextMessage outMsg = new OutTextMessage();
		initTestOutTextMessage(outMsg, inTextMessage);
		outMsg.setContent("processInTextMessage() 方法测试成功");
		
		// 可以 render 各种类型的消息
		// 具体看 com.jfinal.weixin.sdk.message.out 包下面的各类消息类
		render(outMsg);
	}
	
	protected void processInImageMessage(InImageMessage inImageMessage) {
		OutTextMessage outMsg = new OutTextMessage();
		initTestOutTextMessage(outMsg, inImageMessage);
		outMsg.setContent("processInImageMessage() 方法测试成功");
		render(outMsg);
	}
	
	protected void processInVoiceMessage(InVoiceMessage inVoiceMessage) {
		OutTextMessage outMsg = new OutTextMessage();
		initTestOutTextMessage(outMsg, inVoiceMessage);
		outMsg.setContent("processInVoiceMessage() 方法测试成功");
		render(outMsg);
	}
	
	protected void processInVideoMessage(InVideoMessage inVideoMessage) {
		OutTextMessage outMsg = new OutTextMessage();
		initTestOutTextMessage(outMsg, inVideoMessage);
		outMsg.setContent("processInVideoMessage() 方法测试成功");
		render(outMsg);
	}
	
	protected void processInLocationMessage(InLocationMessage inLocationMessage) {
		OutTextMessage outMsg = new OutTextMessage();
		initTestOutTextMessage(outMsg, inLocationMessage);
		outMsg.setContent("processInLocationMessage() 方法测试成功");
		render(outMsg);
	}
	
	protected void processInLinkMessage(InLinkMessage inLinkMessage) {
		OutTextMessage outMsg = new OutTextMessage();
		initTestOutTextMessage(outMsg, inLinkMessage);
		outMsg.setContent("processInLinkMessage() 方法测试成功");
		render(outMsg);
	}
	
	protected void processInFollowEvent(InFollowEvent inFollowEvent) {
		OutTextMessage outMsg = new OutTextMessage();
		initTestOutTextMessage(outMsg, inFollowEvent);
		outMsg.setContent("processInFollowEvent() 方法测试成功");
		render(outMsg);
	}
	
	protected void processInQrCodeEvent(InQrCodeEvent inQrCodeEvent) {
		OutTextMessage outMsg = new OutTextMessage();
		initTestOutTextMessage(outMsg, inQrCodeEvent);
		outMsg.setContent("processInQrCodeEvent() 方法测试成功");
		render(outMsg);
	}
	
	protected void processInLocationEvent(InLocationEvent inLocationEvent) {
		OutTextMessage outMsg = new OutTextMessage();
		initTestOutTextMessage(outMsg, inLocationEvent);
		outMsg.setContent("processInLocationEvent() 方法测试成功");
		render(outMsg);
	}
	
	protected void processInMenuEvent(InMenuEvent inMenuEvent) {
		OutTextMessage outMsg = new OutTextMessage();
		initTestOutTextMessage(outMsg, inMenuEvent);
		outMsg.setContent("processInMenuEvent() 方法测试成功");
		render(outMsg);
	}
	
	protected void processInSpeechRecognitionResults(InSpeechRecognitionResults inSpeechRecognitionResults) {
		OutTextMessage outMsg = new OutTextMessage();
		initTestOutTextMessage(outMsg, inSpeechRecognitionResults);
		outMsg.setContent("processInSpeechRecognitionResults() 方法测试成功");
		render(outMsg);
	}
}






