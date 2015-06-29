/**
 * Copyright (c) 2011-2015, Unas 小强哥 (unas@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.api;

import com.jfinal.kit.HttpKit;

/**
 * 将一条长链接转成短链接 API
 * https://api.weixin.qq.com/cgi-bin/shorturl?access_token=ACCESS_TOKEN
 */
public class ShorturlApi
{
	private static String apiUrl = "https://api.weixin.qq.com/cgi-bin/shorturl?access_token=";

	public static ApiResult getShorturl(String jsonStr) {
		String jsonResult = HttpKit.post(apiUrl + AccessTokenApi.getAccessToken().getAccessToken(), jsonStr);
		return new ApiResult(jsonResult);
	}
}
