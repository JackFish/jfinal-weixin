package com.jfinal.weixin.sdk.cache;

import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;
import com.jfinal.weixin.sdk.api.AccessToken;

public class RedisAccessTokenCache implements IAccessTokenCache {
	
	private final String ACCESS_TOKEN_PREFIX = "access_token:";
	
	private Cache cache = Redis.use();
	
	@Override
	public <T> T get(String appId) {
		return cache.get(ACCESS_TOKEN_PREFIX + appId);
	}
	
	@Override
	public void set(String appId, AccessToken accessToken) {
		cache.setex(ACCESS_TOKEN_PREFIX + appId, DEFAULT_TIME_OUT, accessToken);
	}
	
	@Override
	public void remove(String appId) {
		cache.del(ACCESS_TOKEN_PREFIX + appId);
	}
	
}
