package com.jfinal.weixin.sdk.message.in.speech_recognition;

import com.jfinal.weixin.sdk.message.in.InMessage;


/**
	接收语音识别结果
	<xml>
		<ToUserName><![CDATA[toUser]]></ToUserName>
		<FromUserName><![CDATA[fromUser]]></FromUserName>
		<CreateTime>1357290913</CreateTime>
		<MsgType><![CDATA[voice]]></MsgType>
			<MediaId><![CDATA[media_id]]></MediaId>
			<Format><![CDATA[Format]]></Format>
			<Recognition><![CDATA[腾讯微信团队]]></Recognition>
			<MsgId>1234567890123456</MsgId>
	</xml>
 */
public class InSpeechRecognitionResults extends InMessage {
	
	private String mediaId;
	private String format;
	private String recognition;
	private String msgId;
	
	public InSpeechRecognitionResults(String toUserName, String fromUserName, Integer createTime, String msgType) {
		super(toUserName, fromUserName, createTime, msgType);
	}
	
	public String getMediaId() {
		return mediaId;
	}
	
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	
	public String getFormat() {
		return format;
	}
	
	public void setFormat(String format) {
		this.format = format;
	}
	
	public String getRecognition() {
		return recognition;
	}
	
	public void setRecognition(String recognition) {
		this.recognition = recognition;
	}
	
	public String getMsgId() {
		return msgId;
	}
	
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
}

