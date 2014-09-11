package com.jfinal.weixin.sdk.jfinal;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.weixin.sdk.kit.HttpKit;
import com.jfinal.weixin.sdk.kit.SignatureCheckKit;
import com.jfinal.weixin.sdk.message.InMessageParaser;
import com.jfinal.weixin.sdk.message.in.InImageMessage;
import com.jfinal.weixin.sdk.message.in.InLinkMessage;
import com.jfinal.weixin.sdk.message.in.InLocationMessage;
import com.jfinal.weixin.sdk.message.in.InMessage;
import com.jfinal.weixin.sdk.message.in.InTextMessage;
import com.jfinal.weixin.sdk.message.in.InVideoMessage;
import com.jfinal.weixin.sdk.message.in.InVoiceMessage;
import com.jfinal.weixin.sdk.message.in.event.InFollowEvent;
import com.jfinal.weixin.sdk.message.in.event.InLocationEvent;
import com.jfinal.weixin.sdk.message.in.event.InMenuEvent;
import com.jfinal.weixin.sdk.message.in.event.InQrCodeEvent;
import com.jfinal.weixin.sdk.message.in.speech_recognition.InSpeechRecognitionResults;
import com.jfinal.weixin.sdk.message.out.OutMessage;

/**
 * 自动接收微信服务器消息，自动解析成 InMessage 并分发到相应的处理方法
 */
// @Before(SignatureCheckInterceptor.class)
public abstract class WeixinController extends Controller {
	
	/**
	 * 配置为微信开发者中心的 URL
	 * 这里应该要包含 become_developer 的 api
	 */
	public void index() {
		// 成为开发者判断
		if (StrKit.notBlank(getPara("echostr"))) {
			becomeDeveloper();
			return ;
		}
		
		InMessage msg = getInMessage();
		if (msg instanceof InTextMessage)
			processInTextMessage((InTextMessage)msg);
		else if (msg instanceof InImageMessage)
			processInImageMessage((InImageMessage)msg);
		else if (msg instanceof InVoiceMessage)
			processInVoiceMessage((InVoiceMessage)msg);
		else if (msg instanceof InVideoMessage)
			processInVideoMessage((InVideoMessage)msg);
		else if (msg instanceof InLocationMessage)
			processInLocationMessage((InLocationMessage)msg);
		else if (msg instanceof InLinkMessage)
			processInLinkMessage((InLinkMessage)msg);
		else if (msg instanceof InFollowEvent)
			processInFollowEvent((InFollowEvent)msg);
		else if (msg instanceof InQrCodeEvent)
			processInQrCodeEvent((InQrCodeEvent)msg);
		else if (msg instanceof InLocationEvent)
			processInLocationEvent((InLocationEvent)msg);
		else if (msg instanceof InMenuEvent)
			processInMenuEvent((InMenuEvent)msg);
		else if (msg instanceof InSpeechRecognitionResults)
			processInSpeechRecognitionResults((InSpeechRecognitionResults)msg);
		else
			renderText("未能识别的消息类型!");
	}
	
	/**
	 * 成为开发者
	 */
	private void becomeDeveloper() {
		String echostr = getPara("echostr");
		String signature = getPara("signature");
        String timestamp = getPara("timestamp");
        String nonce = getPara("nonce");
		boolean isOk = SignatureCheckKit.me.checkSignature(signature, timestamp, nonce);
		if (isOk)
			renderText(echostr);
		else
			renderText("验证失败：become_developer");
	}
	
	/**
	 * 在接收到消息后服务器响应消息
	 */
	public void render(OutMessage outMessage) {
		render(new OutMessageRender(outMessage.getClass().getSimpleName()));
	}
	
	public InMessage getInMessage() {
		String messageXml = HttpKit.readData(getRequest());
		return InMessageParaser.parse(messageXml);
	}
	
	// 处理接收到的文本消息
	protected abstract void processInTextMessage(InTextMessage inTextMessage);
	
	// 处理接收到的图片消息
	protected abstract void processInImageMessage(InImageMessage inImageMessage);
	
	// 处理接收到的语音消息
	protected abstract void processInVoiceMessage(InVoiceMessage inVoiceMessage);
	
	// 处理接收到的视频消息
	protected abstract void processInVideoMessage(InVideoMessage inVideoMessage);
	
	// 处理接收到的地址位置消息
	protected abstract void processInLocationMessage(InLocationMessage inLocationMessage);

	// 处理接收到的链接消息
	protected abstract void processInLinkMessage(InLinkMessage inLinkMessage);
	
	// 处理接收到的关注/取消关注事件
	protected abstract void processInFollowEvent(InFollowEvent inFollowEvent);
	
	// 处理接收到的扫描带参数二维码事件
	protected abstract void processInQrCodeEvent(InQrCodeEvent inQrCodeEvent);
	
	// 处理接收到的上报地理位置事件
	protected abstract void processInLocationEvent(InLocationEvent inLocationEvent);
	
	// 处理接收到的自定义菜单事件
	protected abstract void processInMenuEvent(InMenuEvent inMenuEvent);
	
	// 处理接收到的语音识别结果
	protected abstract void processInSpeechRecognitionResults(InSpeechRecognitionResults inSpeechRecognitionResults);
}











