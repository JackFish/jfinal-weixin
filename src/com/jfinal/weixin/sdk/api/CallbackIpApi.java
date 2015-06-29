/**
 * Copyright (c) 2011-2015, Unas 小强哥 (unas@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.api;

import com.jfinal.kit.HttpKit;

/**
 * 获取微信服务器IP地址</br>
 * 如果公众号基于安全等考虑，需要获知微信服务器的IP地址列表，以便进行相关限制，可以通过该接口获得微信服务器IP地址列表。
 */
public class CallbackIpApi
{
	private static String apiUrl = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=";

	/**
	 * 获取微信服务器IP地址
	 */
	public static ApiResult getCallbackIp() {
		String jsonResult = HttpKit.get(apiUrl + AccessTokenApi.getAccessToken().getAccessToken());
		return new ApiResult(jsonResult);
	}
}
