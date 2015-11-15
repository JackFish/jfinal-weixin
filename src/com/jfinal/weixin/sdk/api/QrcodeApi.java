/**
 * Copyright (c) 2011-2015, Unas 小强哥 (unas@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.api;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.kit.HttpKit;
import com.jfinal.weixin.sdk.utils.JsonUtils;

/**
 * 生成带参数的二维码 API
 * https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN
 */
public class QrcodeApi
{
	private static String apiUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";
	
	public static ApiResult create(String jsonStr) {
		String jsonResult = HttpKit.post(apiUrl + AccessTokenApi.getAccessTokenStr(), jsonStr);
		return new ApiResult(jsonResult);
	}
	
	/**
	 * 创建临时二维码
	 * @param expireSeconds 该二维码有效时间，以秒为单位。 最大不超过604800（即7天）。
	 * @param sceneId 场景值ID，临时二维码时为32位非0整型
	 * @return ApiResult 二维码信息
	 */
	public static ApiResult createTemporary(int expireSeconds, int sceneId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("expire_seconds", expireSeconds);
		params.put("action_name", "QR_SCENE");
		
		Map<String, Object> actionInfo = new HashMap<String, Object>();
		Map<String, Object> scene = new HashMap<String, Object>();
		scene.put("scene_id", sceneId);
		
		actionInfo.put("scene", scene);
		params.put("action_info", actionInfo);
		return create(JsonUtils.toJson(params));
	}
	
	/**
	 * 创建永久二维码
	 * @param sceneId 场景值ID，永久二维码时最大值为100000（目前参数只支持1--100000）
	 * @return ApiResult 二维码信息
	 */
	public static ApiResult createPermanent(int sceneId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("action_name", "QR_LIMIT_SCENE");
		
		Map<String, Object> actionInfo = new HashMap<String, Object>();
		Map<String, Object> scene = new HashMap<String, Object>();
		scene.put("scene_id", sceneId);
		
		actionInfo.put("scene", scene);
		params.put("action_info", actionInfo);
		return create(JsonUtils.toJson(params));
	}
	
	/**
	 * 创建永久二维码
	 * @param sceneStr 场景值ID（字符串形式的ID），字符串类型，长度限制为1到64，仅永久二维码支持此字段
	 * @return ApiResult 二维码信息
	 */
	public static ApiResult createTemporary(String sceneStr) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("action_name", "QR_LIMIT_STR_SCENE");
		
		Map<String, Object> actionInfo = new HashMap<String, Object>();
		Map<String, Object> scene = new HashMap<String, Object>();
		scene.put("scene_str", sceneStr);
		
		actionInfo.put("scene", scene);
		params.put("action_info", actionInfo);
		return create(JsonUtils.toJson(params));
	}
	
	private static String showQrcodeUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=";
	
	/**
	 * 通过ticket换取二维码地址
	 * @param ticket 换取二维码参数
	 * @return String url
	 */
	public static String getShowQrcodeUrl(String ticket) {
		return showQrcodeUrl + ticket;
	}
}
