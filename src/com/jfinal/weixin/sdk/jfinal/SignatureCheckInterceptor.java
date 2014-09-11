package com.jfinal.weixin.sdk.jfinal;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;
import com.jfinal.log.Logger;
import com.jfinal.weixin.sdk.kit.SignatureCheckKit;

public class SignatureCheckInterceptor implements Interceptor {
	
	private static final Logger log = Logger.getLogger(SignatureCheckInterceptor.class);
	
	public void intercept(ActionInvocation ai) {
		Controller controller = ai.getController();
		if (SignatureCheckKit.me.checkSignature(controller)) {
			ai.invoke();
		}
		else {
			log.error("签名验证失败");
			controller.renderText("check signature error");
		}
	}
}




