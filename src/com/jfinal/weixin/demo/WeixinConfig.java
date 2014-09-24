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
import com.jfinal.weixin.sdk.api.ApiConfig;

public class WeixinConfig extends JFinalConfig {
	public void configConstant(Constants me) {
		loadPropertyFile("a_little_config.txt");
		me.setDevMode(true);
		ApiConfig.setUrl(getProperty("url"));
		ApiConfig.setToken(getProperty("token"));
		ApiConfig.setAppId(getProperty("appId"));
		ApiConfig.setAppSecret(getProperty("appSecret"));
	}
	
	public void configRoute(Routes me) {
		me.add("/weixin", DemoController.class);
	}
	
	public void configPlugin(Plugins me) {
		
	}
	
	public void configInterceptor(Interceptors me) {
		
	}
	
	public void configHandler(Handlers me) {
		
	}
	
	public static void main(String[] args) {
		JFinal.start("webapp", 80, "/", 5);
	}
}
