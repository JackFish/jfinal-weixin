/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.kit;

import java.util.Properties;

public class PropKit {
	
	private PropKit() {}
	
	private static Properties properties = new Properties();
	
	public static void load(String fileName) {
		try {
		properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String get(String key) {
		return properties.getProperty(key);
	}
}


