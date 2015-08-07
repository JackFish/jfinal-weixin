/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.api;


/**
 * 封装 access_token
 */
public class AccessToken {
	
	private String access_token;	// 正确获取到 access_token 时有值
	private Integer expires_in;		// 正确获取到 access_token 时有值
	private Integer errcode;		// 出错时有值
	private String errmsg;			// 出错时有值
	
	private Long expiredTime;		// 正确获取到 access_token 时有值，存放过期时间
	private String json;
	
	public AccessToken(String jsonStr) {
		this.json = jsonStr;
		
		try {
			ApiResult ar = new ApiResult(jsonStr);
			access_token = ar.getStr("access_token");
			expires_in = ar.getInt("expires_in");
			errcode = ar.getInt("errcode");
			errmsg = ar.getStr("errmsg");
			
			if (expires_in != null)
				expiredTime = System.currentTimeMillis() + ((expires_in -5) * 1000);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public String getJson() {
		return json;
	}
	
	public boolean isAvailable() {
		if (expiredTime == null)
			return false;
		if (errcode != null)
			return false;
		if (expiredTime < System.currentTimeMillis())
			return false;
		return access_token != null;
	}
	
	public String getAccessToken() {
		return access_token;
	}
	
	public Integer getExpiresIn() {
		return expires_in;
	}
	
	public Integer getErrorCode() {
		return errcode;
	}
	
	public String getErrorMsg() {
		if (errcode != null) {
			String result = ReturnCode.get(errcode);
			if (result != null)
				return result;
		}
		return errmsg;
	}
}
