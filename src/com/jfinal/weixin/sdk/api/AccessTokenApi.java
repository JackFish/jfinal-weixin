/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.api;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jfinal.kit.HttpKit;
import com.jfinal.weixin.sdk.kit.ParaMap;

/**
 * 认证并获取 access_token API
 * http://mp.weixin.qq.com/wiki/index.php?title=%E8%8E%B7%E5%8F%96access_token
 */
public class AccessTokenApi {
	
	// "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	private static String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
	
	// 利用 appId 与 accessToken 建立关联，支持多账户
	private static Map<String, AccessToken> map = new ConcurrentHashMap<String, AccessToken>();	// private static AccessToken accessToken;
	
	/**
	 * 从缓存中获取 access token，如果未取到或者 access token 不可用则先更新再获取
	 */
	public static AccessToken getAccessToken() {
		String appId = ApiConfigKit.getApiConfig().getAppId();
		AccessToken result = map.get(appId);
		if (result != null && result.isAvailable())
			return result;
		
		refreshAccessToken();
		return map.get(appId);
	}
	
	/**
	 * 强制更新 access token 值
	 */
	public static synchronized void refreshAccessToken() {
		ApiConfig ac = ApiConfigKit.getApiConfig();
		AccessToken result = null;
		for (int i=0; i<3; i++) {	// 最多三次请求
			String appId = ac.getAppId();
			String appSecret = ac.getAppSecret();
			Map<String, String> queryParas = ParaMap.create("appid", appId).put("secret", appSecret).getData();
			String json = HttpKit.get(url, queryParas);
			result = new AccessToken(json);
			
			if (result.isAvailable())
				break;
		}
		
		// 三次请求如果仍然返回了不可用的 access token 仍然 put 进去，便于上层通过 AccessToken 中的属性判断底层的情况
		map.put(ac.getAppId(), result);
	}
	
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		ApiConfig ac = new ApiConfig();
		ac.setAppId("wx9803d1188fa5fbda");
		ac.setAppSecret("db859c968763c582794e7c3d003c3d87");
		ApiConfigKit.setThreadLocalApiConfig(ac);
		
		AccessToken at = getAccessToken();
		if (at.isAvailable())
			System.out.println("access_token : " + at.getAccessToken());
		else
			System.out.println(at.getErrorCode() + " : " + at.getErrorMsg());
	}
}
