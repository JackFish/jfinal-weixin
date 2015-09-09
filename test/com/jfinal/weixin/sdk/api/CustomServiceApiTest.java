package com.jfinal.weixin.sdk.api;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.weixin.sdk.api.CustomServiceApi.Articles;

public class CustomServiceApiTest {

	// 请找有权限的帐号测试
	public static void main(String[] args) {
		String openId = "oOGf-jgjmwxFVU66D-lFO2AFK8ic";

		// 测试发送纯文本：pass
		System.out.println(CustomServiceApi.sendText(openId, "hello JFinal!"));

		// 测试发图文：pass
		List<Articles> articles = new ArrayList<Articles>();
		for (int i = 0; i < 3; i++) {
			Articles n = new Articles();
			n.setTitle("测试" + i);
			n.setDescription("JFinal-weixin CustomServiceApiTest~");
			n.setUrl("http://git.oschina.net/jfinal/jfinal-weixin");
			n.setPicurl("http://static.oschina.net/uploads/space/2015/0211/181947_2431_201137.jpg");
			articles.add(n);
		}
		System.out.println(CustomServiceApi.sendNews(openId, articles));
	}
}
