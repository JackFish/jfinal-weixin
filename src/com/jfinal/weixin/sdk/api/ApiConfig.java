package com.jfinal.weixin.sdk.api;

public class ApiConfig {
	
	private static String url = null;
	private static String token = null;
	private static String appId = null;
	private static String appSecret = null;
	
	// 开发模式将输出消息交互 xml 到控制台
	private static boolean devMode = false;
	
	public static void setDevMode(boolean devMode) {
		ApiConfig.devMode = devMode;
	}
	
	public static boolean isDevMode() {
		return devMode;
	}
	
	public static void init(String url, String token) {
		setUrl(url);
		setToken(token);
	}
	
	public static void init(String url, String token, String appId, String appSecret) {
		setUrl(url);
		setToken(token);
		setAppId(appId);
		setAppSecret(appSecret);
	}
	
	public static String getUrl() {
		if (url == null)
			throw new RuntimeException("init ApiConfig.setUrl(...) first");
		return url;
	}
	
	public static void setUrl(String url) {
		if (url == null)
			throw new IllegalArgumentException("url can not be null");
		ApiConfig.url = url;
	}
	
	public static String getToken() {
		if (token == null)
			throw new RuntimeException("init ApiConfig.setToken(...) first");
		return token;
	}
	
	public static void setToken(String token) {
		if (token == null)
			throw new IllegalArgumentException("token can not be null");
		ApiConfig.token = token;
	}
	
	public static String getAppId() {
		if (appId == null)
			throw new RuntimeException("init ApiConfig.setAppId(...) first");
		return appId;
	}
	
	public static void setAppId(String appId) {
		if (appId == null)
			throw new IllegalArgumentException("appId can not be null");
		ApiConfig.appId = appId;
	}
	
	public static String getAppSecret() {
		if (appSecret == null)
			throw new RuntimeException("init ApiConfig.setAppSecret(...) first");
		return appSecret;
	}
	
	public static void setAppSecret(String appSecret) {
		if (appSecret == null)
			throw new IllegalArgumentException("appSecret can not be null");
		ApiConfig.appSecret = appSecret;
	}
}




