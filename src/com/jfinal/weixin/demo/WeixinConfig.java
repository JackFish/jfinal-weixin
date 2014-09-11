package com.jfinal.weixin.demo;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;

public class WeixinConfig extends JFinalConfig {
	public void configConstant(Constants me) {
		me.setDevMode(true);
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
}
