package com.jfinal.weixin.sdk.message.in;

/**
	接收文本消息
	<xml>
		<ToUserName><![CDATA[toUser]]></ToUserName>
		<FromUserName><![CDATA[fromUser]]></FromUserName> 
		<CreateTime>1348831860</CreateTime>
		<MsgType><![CDATA[text]]></MsgType>
			<Content><![CDATA[this is a test]]></Content>
			<MsgId>1234567890123456</MsgId>
	</xml>
 */
public class InTextMessage extends InMessage {
	
	private String content;
	private String msgId;
	
	public InTextMessage(String toUserName, String fromUserName, Integer createTime, String msgType) {
		super(toUserName, fromUserName, createTime, msgType);
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getMsgId() {
		return msgId;
	}
	
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	
}




