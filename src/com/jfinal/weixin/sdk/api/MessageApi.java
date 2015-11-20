package com.jfinal.weixin.sdk.api;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.kit.HttpKit;
import com.jfinal.weixin.sdk.utils.JsonUtils;

/**
 * 高级群发相关接口
 * @author L.cm
 */
public class MessageApi {
	
	private static ApiResult post(String baseUrl, String jsonStr) {
		String url = baseUrl + AccessTokenApi.getAccessTokenStr();
		String jsonResult = HttpKit.post(url, jsonStr);
		return new ApiResult(jsonResult);
	}
	
	private static String sendAllUrl = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=";
	
	/**
	 * 根据分组进行群发【订阅号与服务号认证后均可用】
	 * @param jsonStr
	 * @return
	 */
	public static ApiResult sendAll(String jsonStr) {
		return post(sendAllUrl, jsonStr);
	}
	
	private static String sendUrl = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=";
	
	/**
	 * 根据OpenID列表群发【订阅号不可用，服务号认证后可用】
	 * @param jsonStr
	 * @return
	 */
	public static ApiResult send(String jsonStr) {
		return post(sendUrl, jsonStr);
	}
	
	private static String previewUrl = "https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=";
	
	/**
	 * 预览接口【订阅号与服务号认证后均可用】
	 * @param jsonStr
	 * @return
	 */
	public static ApiResult preview(String jsonStr) {
		return post(previewUrl, jsonStr);
	}
	
	private static String getUrl = "https://api.weixin.qq.com/cgi-bin/message/mass/get?access_token=";
	
	/**
	 * 查询群发消息发送状态【订阅号与服务号认证后均可用】
	 * @param msgId 群发消息后返回的消息id
	 * @return ApiResult
	 */
	public static ApiResult get(String msgId) {
		Map<String, String> mapData = new HashMap<String, String>();
		mapData.put("msg_id", msgId);
		
		return post(getUrl, JsonUtils.toJson(mapData));
	}
	
	private static String deleteUrl = "https://api.weixin.qq.com/cgi-bin/message/mass/delete?access_token=";
	
	/**
	 * 删除群发【订阅号与服务号认证后均可用】
	 * 由于技术限制，群发只有在刚发出的半小时内可以删除，发出半小时之后将无法被删除。
	 * @param msgId 群发消息后返回的消息id
	 * @return ApiResult
	 */
	public static ApiResult delete(String msgId) {
		Map<String, String> mapData = new HashMap<String, String>();
		mapData.put("msg_id", msgId);
		
		return post(deleteUrl, JsonUtils.toJson(mapData));
	}
	
}
