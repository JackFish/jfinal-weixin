/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.api;

import com.jfinal.kit.HttpKit;

/**
 * 模板消息 API
 */
public class TemplateMsgApi {
	
	private static String sendTemplateMsg = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
	
	/**
	 * 发送模板消息
	 */
	public static ApiResult sendTemplateMsg(String jsonStr) {
		String jsonResult = HttpKit.post(sendTemplateMsg + AccessTokenApi.getAccessToken().getAccessToken(), jsonStr);
		return new ApiResult(jsonResult);
	}
}


