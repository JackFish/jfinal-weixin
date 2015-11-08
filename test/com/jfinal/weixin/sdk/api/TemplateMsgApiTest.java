package com.jfinal.weixin.sdk.api;

/**
 * 模板消息测试
 * @author L.cm
 * email: 596392912@qq.com
 * site:http://www.dreamlu.net
 * date: 2015年9月9日 下午11:03:48
 */
public class TemplateMsgApiTest {

	public static void main(String[] args) {
		// 模板消息，发送测试：pass
		ApiResult result = TemplateMsgApi.send(TemplateData.New()
			// 消息接收者
			.setTouser("oOGf-jgjmwxFVU66D-lFO2AFK8ic")
			// 模板id
			.setTemplate_id("UsOTFJfm7-XfskPd5p2wlBXYUjdwjUTZxnMXbOfVQ0A")
			.setTopcolor("#eb414a")
			.setUrl("http://m.xxxx.cn/qrcode/t/xxxxxx")
			
			// 模板参数
			.add("first", "验票成功！\n", "#999")
			.add("keyword1", "xxxxxx", "#999")
			.add("keyword2", "2014年12月27日 19:30", "#999")
			.add("keyword3", "xxxxx", "#999")
			.add("keyword4", "xxxxxxxx", "#999")
			.add("keyword5", "xxx元", "#999")
			.add("remark", "\nxxxxxxxxxx。", "#999")
			.build());

		System.out.println(result);
	}
}
