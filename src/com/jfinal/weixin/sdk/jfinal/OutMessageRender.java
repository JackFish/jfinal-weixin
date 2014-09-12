/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.jfinal;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import com.jfinal.render.Render;
import com.jfinal.render.RenderException;
import com.jfinal.weixin.sdk.message.out.OutMusicMessage;
import com.jfinal.weixin.sdk.message.out.OutNewsMessage;
import com.jfinal.weixin.sdk.message.out.OutImageMessage;
import com.jfinal.weixin.sdk.message.out.OutTextMessage;
import com.jfinal.weixin.sdk.message.out.OutVideoMessage;
import com.jfinal.weixin.sdk.message.out.OutVoiceMessage;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

@SuppressWarnings("serial")
public class OutMessageRender extends Render {
	
	private transient static final String contentType = "text/xml; charset=" + getEncoding();
	private static Configuration config = init();
	
	private String outMessageName;
	
	public OutMessageRender(String outMessageName) {
		this.outMessageName = outMessageName;
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void render() {
		response.setContentType(contentType);
        Enumeration<String> attrs = request.getAttributeNames();
		Map root = new HashMap();
		while (attrs.hasMoreElements()) {
			String attrName = attrs.nextElement();
			root.put(attrName, request.getAttribute(attrName));
		}
		
		PrintWriter writer = null;
        try {
			Template template = config.getTemplate(outMessageName, getEncoding());
			writer = response.getWriter();
			template.process(root, writer);		// Merge the data-model and the template
		} catch (Exception e) {
			throw new RenderException(e);
		}
		finally {
			if (writer != null)
				writer.close();
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
		loader.putTemplate(OutTextMessage.class.getSimpleName(), OutTextMessage.TEMPLATE);
		// news 图文消息
		loader.putTemplate(OutNewsMessage.class.getSimpleName(), OutNewsMessage.TEMPLATE);
		// image 图片消息
		loader.putTemplate(OutImageMessage.class.getSimpleName(), OutImageMessage.TEMPLATE);
		//voice 语音消息
		loader.putTemplate(OutVoiceMessage.class.getSimpleName(), OutVoiceMessage.TEMPLATE);
		// video 视频消息
		loader.putTemplate(OutVideoMessage.class.getSimpleName(), OutVideoMessage.TEMPLATE);
		// music 音乐消息
		loader.putTemplate(OutMusicMessage.class.getSimpleName(), OutMusicMessage.TEMPLATE);
	}
}



