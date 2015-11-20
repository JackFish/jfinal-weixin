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

	
	
	
	private static String getUrl = "https://api.weixin.qq.com/cgi-bin/message/mass/get?access_token=";

	/**
	 * 查询群发消息发送状态【订阅号与服务号认证后均可用】
	 * @param msgId 群发消息后返回的消息id
	 * @return ApiResult
	 */
	public static ApiResult get(String msgId) {
		String url = getUrl + AccessTokenApi.getAccessTokenStr();
		
		Map<String, String> mapData = new HashMap<String, String>();
		mapData.put("msg_id", msgId);
		
		String jsonResult = HttpKit.post(url, JsonUtils.toJson(mapData));
		return new ApiResult(jsonResult);
	}
}
