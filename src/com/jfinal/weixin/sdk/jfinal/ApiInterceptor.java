package com.jfinal.weixin.sdk.jfinal;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;
import com.jfinal.weixin.sdk.api.ApiConfigKit;

/**
 * ApiController 为 ApiController 绑定 ApiConfig 对象到当前线程，
 * 以便在后续的操作中可以使用 ApiConfigKit.getApiConfig() 获取到该对象
 */
public class ApiInterceptor implements Interceptor {
	
	public void intercept(ActionInvocation ai) {
		Controller controller = ai.getController();
		if (controller instanceof ApiController == false)
			throw new RuntimeException("控制器需要继承 ApiController");
		
		try {
			ApiConfigKit.setThreadLocalApiConfig(((ApiController)controller).getApiConfig());
			ai.invoke();
		}
		finally {
			ApiConfigKit.removeThreadLocalApiConfig();
		}
	}
}

