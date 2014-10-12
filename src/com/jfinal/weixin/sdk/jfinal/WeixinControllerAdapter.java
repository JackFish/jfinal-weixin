/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.jfinal;

import com.jfinal.weixin.sdk.msg.in.InImageMsg;
import com.jfinal.weixin.sdk.msg.in.InLinkMsg;
import com.jfinal.weixin.sdk.msg.in.InLocationMsg;
import com.jfinal.weixin.sdk.msg.in.InTextMsg;
import com.jfinal.weixin.sdk.msg.in.InVideoMsg;
import com.jfinal.weixin.sdk.msg.in.InVoiceMsg;
import com.jfinal.weixin.sdk.msg.in.event.InFollowEvent;
import com.jfinal.weixin.sdk.msg.in.event.InLocationEvent;
import com.jfinal.weixin.sdk.msg.in.event.InMenuEvent;
import com.jfinal.weixin.sdk.msg.in.event.InQrCodeEvent;
import com.jfinal.weixin.sdk.msg.in.speech_recognition.InSpeechRecognitionResults;

/**
 * WeixinControllerAdapter
 */
public abstract class WeixinControllerAdapter extends WeixinController {
	
	protected abstract void processInFollowEvent(InFollowEvent inFollowEvent);
	
	protected abstract void processInTextMsg(InTextMsg inTextMsg);
	
	protected abstract void processInMenuEvent(InMenuEvent inMenuEvent);
	
	protected void processInImageMsg(InImageMsg inImageMsg) {
		
	}
	
	protected void processInVoiceMsg(InVoiceMsg inVoiceMsg) {
		
	}
	
	protected void processInVideoMsg(InVideoMsg inVideoMsg) {
		
	}
	
	protected void processInLocationMsg(InLocationMsg inLocationMsg) {
		
	}
	
	protected void processInLinkMsg(InLinkMsg inLinkMsg) {
		
	}
	
	protected void processInQrCodeEvent(InQrCodeEvent inQrCodeEvent) {
		
	}
	
	protected void processInLocationEvent(InLocationEvent inLocationEvent) {
		
	}
	
	protected void processInSpeechRecognitionResults(InSpeechRecognitionResults inSpeechRecognitionResults) {
		
	}
}


