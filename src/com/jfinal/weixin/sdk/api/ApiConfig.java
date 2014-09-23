package com.jfinal.weixin.sdk.api;

public class ApiConfig {
	
	private static String url = null;
	private static String token = null;
	
	public static void setUrl(String url) {
		if (url == null)
			throw new IllegalArgumentException("url can not be null");
		ApiConfig.url = url;
	}
	
	public static String getUrl() {
		if (url == null)
			throw new RuntimeException("init ApiConfig.url first");
		return url;
	}
	
	public static void setToken(String token) {
		if (token == null)
			throw new IllegalArgumentException("token can not be null");
		ApiConfig.token = token;
	}
	
	public static String getToken() {
		if (token == null)
			throw new RuntimeException("init ApiConfig.token first");
		return token;
	}
}
