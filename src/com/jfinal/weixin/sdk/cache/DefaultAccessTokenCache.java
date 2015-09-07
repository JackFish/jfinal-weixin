package com.jfinal.weixin.sdk.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.jfinal.weixin.sdk.api.AccessToken;

/**
 * 默认存储与内存中
 */
public class DefaultAccessTokenCache implements IAccessTokenCache {

	private Map<String, AccessToken> map = new ConcurrentHashMap<String, AccessToken>();

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(String appId) {
		return (T) map.get(appId);
	}

	@Override
	public void set(String appId, AccessToken accessToken) {
		map.put(appId, accessToken);
	}

	@Override
	public void remove(String appId) {
		map.remove(appId);
	}

}
