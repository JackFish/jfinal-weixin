/**
 * Copyright (c) 2011-2015, Unas 小强哥 (unas@qq.com).
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.api;

import com.jfinal.kit.HttpKit;
import com.jfinal.weixin.sdk.cache.IAccessTokenCache;
import com.jfinal.weixin.sdk.kit.ParaMap;

/**
 * 
 * 生成签名之前必须先了解一下jsapi_ticket，jsapi_ticket是公众号用于调用微信JS接口的临时票据
 * https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi
 * 
 * 微信卡券接口签名凭证api_ticket
 * https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=wx_card
 */
public class JsTicketApi {

	private static String apiUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";

	static IAccessTokenCache accessTokenCache = ApiConfigKit.getAccessTokenCache();

	/**
	 * JSApi的类型
	 * 
	 * jsapi: 用于分享等js-api
	 * 
	 * wx_card：用于卡券接口签名凭证api_ticket
	 * 
	 */
	public enum JsApiType {
		jsapi, wx_card
	}

	/**
	 * 
	 * http GET请求获得jsapi_ticket（有效期7200秒，开发者必须在自己的服务全局缓存jsapi_ticket）
	 * 
	 * @param jsApiType
	 * @return JsTicket
	 */
	public static JsTicket getTicket(JsApiType jsApiType) {
		String access_token = AccessTokenApi.getAccessTokenStr();
		String appId = ApiConfigKit.getApiConfig().getAppId();
		String key = appId + ':' + jsApiType.name();
		
		JsTicket jsTicket = accessTokenCache.get(key);
		if (null == jsTicket || !jsTicket.isAvailable()) {
			ParaMap pm = ParaMap.create("access_token", access_token).put("type", jsApiType.name());
			jsTicket = new JsTicket(HttpKit.get(apiUrl, pm.getData()));
			// 方便页面中使用
			jsTicket.setAppId(appId);
			accessTokenCache.set(key, jsTicket);
		}
		return jsTicket;
	}

}