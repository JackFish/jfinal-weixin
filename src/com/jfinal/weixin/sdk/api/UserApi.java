/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.api;

import java.util.HashMap;
import java.util.Map;
import com.jfinal.weixin.sdk.kit.HttpKit;

/**
 * 用户管理 API
 * https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
 */
public class UserApi {
	
	private static String getUserInfo = "https://api.weixin.qq.com/cgi-bin/user/info";
	private static String getFollowers = "https://api.weixin.qq.com/cgi-bin/user/get";
	
	public ApiResult getUserInfo(String openId) {
		Map<String, String> qp = new HashMap<String, String>();
		qp.put("access_token", OAuthApi.getAccessToken().getAccessToken());
		qp.put("openid", openId);
		qp.put("lang", "zh_CN");
		return new ApiResult(HttpKit.get(getUserInfo, qp));
	}
	
	public ApiResult getFollowers(String nextOpenid) {
		Map<String, String> qp = new HashMap<String, String>();
		qp.put("access_token", OAuthApi.getAccessToken().getAccessToken());
		if (nextOpenid != null)
			qp.put("next_openid", nextOpenid);
		return new ApiResult(HttpKit.get(getFollowers, qp));
	}
	
	public ApiResult getFollows() {
		return getFollowers(null);
	}
}
