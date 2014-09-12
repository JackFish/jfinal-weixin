/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.message.out;

import java.util.ArrayList;
import java.util.List;

/**
	回复图文消息
	<xml>
		<ToUserName><![CDATA[toUser]]></ToUserName>
		<FromUserName><![CDATA[fromUser]]></FromUserName>
		<CreateTime>12345678</CreateTime>
		<MsgType><![CDATA[news]]></MsgType>
			<ArticleCount>2</ArticleCount>
			<Articles>
				<item>
					<Title><![CDATA[title1]]></Title> 
					<Description><![CDATA[description1]]></Description>
					<PicUrl><![CDATA[picurl]]></PicUrl>
					<Url><![CDATA[url]]></Url>
				</item>
				
				<item>
					<Title><![CDATA[title]]></Title>
					<Description><![CDATA[description]]></Description>
					<PicUrl><![CDATA[picurl]]></PicUrl>
					<Url><![CDATA[url]]></Url>
				</item>
			</Articles>
	</xml> 
 */
public class OutNewsMessage extends OutMessage {
	public static final String TEMPLATE =
			"<xml>\n" +
			"<ToUserName><![CDATA[${__msg.toUser}]]></ToUserName>\n" +
			"<FromUserName><![CDATA[${__msg.fromUser}]]></FromUserName>\n" +
			"<CreateTime>${__msg.createTime}</CreateTime>\n" +
			"<MsgType><![CDATA[${__msg.msgType}]]></MsgType>\n" +
				"<ArticleCount>${__msg.getArticleCount()}</ArticleCount>\n" +
				"<Articles>\n" +
					"<#list __msg.getArticles() as x>\n"+
						"<item>\n" +
							"<Title><![CDATA[${x.title}]]></Title>\n" + 
							"<Description><![CDATA[${x.description}]]></Description>\n" +
							"<PicUrl><![CDATA[${x.picUrl}]]></PicUrl>\n" +
							"<Url><![CDATA[${x.url}]]></Url>\n" +
						"</item>\n" +
					"</#list>\n" +
				"</Articles>\n" +
			"</xml>";
	
	// private Integer articleCount;
	private List<News> articles = new ArrayList<News>();
	
	public OutNewsMessage() {
		this.msgType = "news";
	}
	
	public Integer getArticleCount() {
		// return articleCount;
		return articles.size();
	}
	
//	public void setArticleCount(Integer articleCount) {
//		this.articleCount = articleCount;
//	}
	
	public List<News> getArticles() {
		return articles;
	}
	
	public void setArticles(List<News> articles) {
		this.articles = articles;
	}
}








