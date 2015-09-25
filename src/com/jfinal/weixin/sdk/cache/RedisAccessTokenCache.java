package com.jfinal.weixin.sdk.cache;

import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;

public class RedisAccessTokenCache implements IAccessTokenCache {
	
	private final String ACCESS_TOKEN_PREFIX = "jfinal_weixin:";
	
	private Cache cache = Redis.use();
	
	@Override
	public <T> T get(String key) {
		return cache.get(ACCESS_TOKEN_PREFIX + key);
	}
	
	@Override
	public void set(String key, Object object) {
		cache.setex(ACCESS_TOKEN_PREFIX + key, DEFAULT_TIME_OUT, object);
	}
	
	@Override
	public void remove(String key) {
		cache.del(ACCESS_TOKEN_PREFIX + key);
	}
	
}
