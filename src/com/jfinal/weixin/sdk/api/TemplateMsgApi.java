/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.api;

import com.jfinal.kit.HttpKit;

/**
 * http://www.oschina.net/code/snippet_923337_44560
 * template msg api
 * 
 * 
 * 
 * 还有个问题

小强哥-北京 2014/12/11 17:41:33
发送了模板消息以后，微信会回发一个通知消息，目前的msg不支持，抛了个异常

小强哥-北京 2014/12/11 17:41:51
java.lang.RuntimeException: 无法识别的事件类型，请查阅微信公众平台开发文档
	at com.jfinal.weixin.sdk.msg.InMsgParaser.parseInEvent(InMsgParaser.java:192)
	at com.jfinal.weixin.sdk.msg.InMsgParaser.doParse(InMsgParaser.java:72)

 */
public class TemplateMsgApi
{
	
	private static String sendTemplateMsg = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";

	/**
	 * 发送模板消息
	 */
	public static ApiResult sendTemplateMsg(String jsonStr) {
		String jsonResult = HttpKit.post(sendTemplateMsg + AccessTokenApi.getAccessToken().getAccessToken(), jsonStr);
		return new ApiResult(jsonResult);
	}
}


