/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.api;

import com.jfinal.kit.HttpKit;
import com.jfinal.weixin.sdk.kit.ParaMap;

/**
 * 用户管理 API
 * https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
 */
public class UserApi {
	
	private static String getUserInfo = "https://api.weixin.qq.com/cgi-bin/user/info";
	private static String getFollowers = "https://api.weixin.qq.com/cgi-bin/user/get";
	private static String batchGetUserInfo = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=";

	public static ApiResult getUserInfo(String openId) {
		ParaMap pm = ParaMap.create("access_token", AccessTokenApi.getAccessToken().getAccessToken()).put("openid", openId).put("lang", "zh_CN");
		return new ApiResult(HttpKit.get(getUserInfo, pm.getData()));
	}
	
	public static ApiResult getFollowers(String nextOpenid) {
		ParaMap pm = ParaMap.create("access_token", AccessTokenApi.getAccessToken().getAccessToken());
		if (nextOpenid != null)
			pm.put("next_openid", nextOpenid);
		return new ApiResult(HttpKit.get(getFollowers, pm.getData()));
	}
	
	public static ApiResult getFollows() {
		return getFollowers(null);
	}

	/**
	 * 批量获取用户基本信息, by Unas
	 */
	public static ApiResult batchGetUserInfo(String jsonStr) {
		String jsonResult = HttpKit.post(batchGetUserInfo + AccessTokenApi.getAccessToken().getAccessToken(), jsonStr);
		return new ApiResult(jsonResult);
	}
}
