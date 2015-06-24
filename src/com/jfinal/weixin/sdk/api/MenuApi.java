/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.api;

import com.jfinal.kit.HttpKit;

/**
 * menu api
 */
public class MenuApi {
	
	private static String getMenu = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=";
	private static String createMenu = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";
	
	/**
	 * 查询菜单
	 */
	public static ApiResult getMenu() {
		String jsonResult = HttpKit.get(getMenu + AccessTokenApi.getAccessToken().getAccessToken());
		return new ApiResult(jsonResult);
	}
	
	/**
	 * 创建菜单
	 */
	public static ApiResult createMenu(String jsonStr) {
		String jsonResult = HttpKit.post(createMenu + AccessTokenApi.getAccessToken().getAccessToken(), jsonStr);
		return new ApiResult(jsonResult);
	}
}


