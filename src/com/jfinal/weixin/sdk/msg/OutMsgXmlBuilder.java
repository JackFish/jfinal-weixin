/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.msg;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import com.jfinal.weixin.sdk.msg.out.OutImageMsg;
import com.jfinal.weixin.sdk.msg.out.OutMsg;
import com.jfinal.weixin.sdk.msg.out.OutMusicMsg;
import com.jfinal.weixin.sdk.msg.out.OutNewsMsg;
import com.jfinal.weixin.sdk.msg.out.OutTextMsg;
import com.jfinal.weixin.sdk.msg.out.OutVideoMsg;
import com.jfinal.weixin.sdk.msg.out.OutVoiceMsg;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 利用 FreeMarker 动态生成 OutMsg xml 内容 
 */
public class OutMsgXmlBuilder {
	
	private static String encoding = "utf-8";
	private static Configuration config = init();
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	public static String build(OutMsg outMsg) {
		if (outMsg == null)
			throw new IllegalArgumentException("参数 OutMsg 不能为 null。");
		
		Map root = new HashMap();
		// 供 OutMsg 的 TEMPLATE 使用
		root.put("__msg", outMsg);
		
		Template template;
		try {
			template = config.getTemplate(outMsg.getClass().getSimpleName(), encoding);
			StringWriter sw = new StringWriter();
			template.process(root, sw);
			
			
			return sw.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private static Configuration init() {
		Configuration config = new Configuration();
		StringTemplateLoader stringLoader = new StringTemplateLoader();
		initStringLoader(stringLoader);
		config.setTemplateLoader(stringLoader);
		return config;
	}
	
	private static void initStringLoader(StringTemplateLoader loader) {
		// text 文本消息
		loader.putTemplate(OutTextMsg.class.getSimpleName(), OutTextMsg.TEMPLATE);
		// news 图文消息
		loader.putTemplate(OutNewsMsg.class.getSimpleName(), OutNewsMsg.TEMPLATE);
		// image 图片消息
		loader.putTemplate(OutImageMsg.class.getSimpleName(), OutImageMsg.TEMPLATE);
		//voice 语音消息
		loader.putTemplate(OutVoiceMsg.class.getSimpleName(), OutVoiceMsg.TEMPLATE);
		// video 视频消息
		loader.putTemplate(OutVideoMsg.class.getSimpleName(), OutVideoMsg.TEMPLATE);
		// music 音乐消息
		loader.putTemplate(OutMusicMsg.class.getSimpleName(), OutMusicMsg.TEMPLATE);
	}
	
	public static void setEncoding(String encoding) {
		OutMsgXmlBuilder.encoding = encoding;
	}
	
	public static String getEncoding() {
		return encoding;
	}
	
	public static void main(String[] args) {
		OutTextMsg msg = new OutTextMsg();
		msg.setToUserName("to james");
		msg.setFromUserName("from james");
		msg.setCreateTime(msg.now());
		msg.setContent("jfinal weixin 极速开发平台碉堡了");
		String xml = OutMsgXmlBuilder.build(msg);
		System.out.println(xml);
	}
}


