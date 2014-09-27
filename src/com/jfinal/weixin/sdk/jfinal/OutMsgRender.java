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
import com.jfinal.weixin.sdk.message.out.OutMsg;
import com.jfinal.weixin.sdk.message.out.OutMusicMsg;
import com.jfinal.weixin.sdk.message.out.OutNewsMsg;
import com.jfinal.weixin.sdk.message.out.OutImageMsg;
import com.jfinal.weixin.sdk.message.out.OutTextMsg;
import com.jfinal.weixin.sdk.message.out.OutVideoMsg;
import com.jfinal.weixin.sdk.message.out.OutVoiceMsg;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

@SuppressWarnings("serial")
public class OutMsgRender extends Render {
	
	private transient static final String contentType = "text/xml; charset=" + getEncoding();
	private static Configuration config = init();
	
	private OutMsg outMsg;
	
	public OutMsgRender(OutMsg outMsg) {
		if (outMsg == null)
			throw new IllegalArgumentException("参数 OutMsg 不能为 null。");
		this.outMsg = outMsg;
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
		
		// 供 OutMessage 的 TEMPLATE 使用
		root.put("__msg", outMsg);
		
		PrintWriter writer = null;
        try {
			Template template = config.getTemplate(outMsg.getClass().getSimpleName(), getEncoding());
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
}



