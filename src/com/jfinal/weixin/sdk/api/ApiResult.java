/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.api;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 封装 API 响应结果
 */
public class ApiResult {
	
	private Map<String, Object> attrs;
	private String json;
	
	/**
	 * 通过 json 构造 ApiResult，注意返回结果不为 json 的 api（如果有的话）
	 */
	@SuppressWarnings("unchecked")
	public ApiResult(String jsonStr) {
		this.json = jsonStr;
		
		try {
			Map<String, Object> temp = new ObjectMapper().readValue(jsonStr, Map.class);
			this.attrs = temp;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public String getJson() {
		return json;
	}
	
	/**
	 * APi 请求是否成功返回
	 */
	public boolean isSucceed() {
		Integer errorCode = getErrorCode();
		// errorCode 为 0 时也可以表示为成功，详见：http://mp.weixin.qq.com/wiki/index.php?title=%E5%85%A8%E5%B1%80%E8%BF%94%E5%9B%9E%E7%A0%81%E8%AF%B4%E6%98%8E
		return (errorCode == null || errorCode == 0);
	}
	
	public Integer getErrorCode() {
		return (Integer)attrs.get("errcode");
	}
	
	public String getErrorMsg() {
		Integer errorCode = getErrorCode();
		if (errorCode != null) {
			String result = ReturnCode.get(errorCode);
			if (result != null)
				return result;
		}
		return (String)attrs.get("errmsg");
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(String name) {
		return (T)attrs.get(name);
	}
	
	public String getStr(String name) {
		return (String)attrs.get(name);
	}
	
	public Integer getInt(String name) {
		return (Integer)attrs.get(name);
	}
	
	public Long getLong(String name) {
		return (Long)attrs.get(name);
	}
	
	public BigInteger getBigInteger(String name) {
		return (BigInteger)attrs.get(name);
	}
	
	public Double getDouble(String name) {
		return (Double)attrs.get(name);
	}
	
	public Boolean getBoolean(String name) {
		return (Boolean)attrs.get(name);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Object> getList(String name) {
		return (ArrayList<Object>)attrs.get(name);
	}
}
