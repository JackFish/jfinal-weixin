/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.demo;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.weixin.sdk.api.ApiConfigKit;

public class WeixinConfig extends JFinalConfig {
	
	/**
	 * 如果生产环境配置文件存在，则优先加载该配置，否则加载开发环境配置文件
	 * @param pro 生产环境配置文件
	 * @param dev 开发环境配置文件
	 */
	public void loadProp(String pro, String dev) {
		try {
			PropKit.use(pro);
		}
		catch (Exception e) {
			PropKit.use(dev);
		}
	}
	
	public void configConstant(Constants me) {
		loadProp("a_little_config_pro.txt", "a_little_config.txt");
		me.setDevMode(PropKit.getBoolean("devMode", false));
		
		// ApiConfigKit 设为开发模式可以在开发阶段输出请求交互的 xml 与 json 数据
		ApiConfigKit.setDevMode(me.getDevMode());
	}
	
	public void configRoute(Routes me) {
		me.add("/msg", WeixinMsgController.class);
		me.add("/api", WeixinApiController.class, "/api");
		me.add("/pay", WeixinPayController.class);
	}
	
	public void configPlugin(Plugins me) {
		// C3p0Plugin c3p0Plugin = new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
		// me.add(c3p0Plugin);
		
		// EhCachePlugin ecp = new EhCachePlugin();
		// me.add(ecp);
		
		// RedisPlugin redisPlugin = new RedisPlugin("weixin", "127.0.0.1");
		// me.add(redisPlugin);
	}
	
	public void configInterceptor(Interceptors me) {
		
	}
	
	public void configHandler(Handlers me) {
		
	}
	
	public void afterJFinalStart() {
		// 1.5 之后支持redis存储access_token、js_ticket，需要先启动RedisPlugin
//		ApiConfigKit.setAccessTokenCache(new RedisAccessTokenCache());
		// 1.6新增的2种初始化
//		ApiConfigKit.setAccessTokenCache(new RedisAccessTokenCache(Redis.use("weixin")));
//		ApiConfigKit.setAccessTokenCache(new RedisAccessTokenCache("weixin"));
	}

	public static void main(String[] args) {
		JFinal.start("webapp", 80, "/", 5);
	}
}
