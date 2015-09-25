package com.jfinal.weixin.sdk.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认存储与内存中
 */
public class DefaultAccessTokenCache implements IAccessTokenCache {
	
	private Map<String, Object> map = new ConcurrentHashMap<String, Object>();
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(String key) {
		return (T) map.get(key);
	}

	@Override
	public void set(String key, Object value) {
		map.put(key, value);
	}
	
	@Override
	public void remove(String key) {
		map.remove(key);
	}
	
}
