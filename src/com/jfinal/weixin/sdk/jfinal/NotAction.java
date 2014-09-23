/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.jfinal;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

/**
 * NotAction
 */
public class NotAction implements Interceptor {
	public void intercept(ActionInvocation ai) {
		ai.getController().renderError(404);
	}
}