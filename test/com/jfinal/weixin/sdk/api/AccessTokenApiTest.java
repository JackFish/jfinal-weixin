package com.jfinal.weixin.sdk.api;

import java.io.IOException;

import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.weixin.sdk.api.AccessToken;
import com.jfinal.weixin.sdk.api.AccessTokenApi;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.cache.RedisAccessTokenCache;

/**
 * AccessTokenApi 测试
 */
public class AccessTokenApiTest {

	public static void init(){
		ApiConfig ac = new ApiConfig();
		ac.setAppId("wx9803d1188fa5fbda");
		ac.setAppSecret("db859c968763c582794e7c3d003c3d87");
		ApiConfigKit.setThreadLocalApiConfig(ac);
	}

	public static void main(String[] args) throws IOException {
		init();
		useRedis();
		test();
	}

	public static void useRedis() {
		new RedisPlugin("main", "localhost").start(); 
		ApiConfigKit.setAccessTokenCache(new RedisAccessTokenCache());
	}

	public static void test() {
		AccessToken at = AccessTokenApi.getAccessToken();
		if (at.isAvailable())
			System.out.println("access_token : " + at.getAccessToken());
		else
			System.out.println(at.getErrorCode() + " : " + at.getErrorMsg());
		
		at = AccessTokenApi.getAccessToken();
		if (at.isAvailable())
			System.out.println("access_token : " + at.getAccessToken());
		else
			System.out.println(at.getErrorCode() + " : " + at.getErrorMsg());
		
		at = AccessTokenApi.getAccessToken();
		if (at.isAvailable())
			System.out.println("access_token : " + at.getAccessToken());
		else
			System.out.println(at.getErrorCode() + " : " + at.getErrorMsg());
	}

}
