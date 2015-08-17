package com.jfinal.weixin.sdk.msg.in.event;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.weixin.sdk.msg.in.InMsg;

/**
 * 
 * 来自：http://my.oschina.net/u/1993676/blog/490124
 * 
 * 用户进入摇一摇界面，在“周边”页卡下摇一摇时，
 * 微信会把这个事件推送到开发者填写的URL（登录公众平台进入开发者中心设置）。
 * 推送内容包含摇一摇时“周边”页卡展示出来的页面所对应的设备信息，
 * 以及附近最多五个属于该公众账号的设备的信息。
 
 <xml>
	 <ToUserName><![CDATA[toUser]]></ToUserName>
	 <FromUserName><![CDATA[fromUser]]></FromUserName>
	 <CreateTime>1433332012</CreateTime>
	 <MsgType><![CDATA[event]]></MsgType>
	 <Event><![CDATA[ShakearoundUserShake]]></Event>
	 <ChosenBeacon>
	 <Uuid><![CDATA[uuid]]></Uuid>
	 <Major>major</Major>
	 <Minor>minor</Minor>
	 <Distance>0.057</Distance>
	 </ChosenBeacon>
		 <AroundBeacons>
			 <AroundBeacon>
			 <Uuid><![CDATA[uuid]]></Uuid>
			 <Major>major</Major>
			 <Minor>minor</Minor>
			 <Distance>166.816</Distance>
			 </AroundBeacon>
			 <AroundBeacon>
			 <Uuid><![CDATA[uuid]]></Uuid>
			 <Major>major</Major>
			 <Minor>minor</Minor>
			 <Distance>15.013</Distance>
			 </AroundBeacon>
		 </AroundBeacons>
 </xml>
*/
public class InShakearoundUserShakeEvent extends InMsg {

	private String event;//事件
	private String uuid;
	private Integer major;
	private Integer minor;
	private Float distance;//设备与用户的距离（浮点数；单位：米）
 
	private List<AroundBeacon> aroundBeaconList = new ArrayList<AroundBeacon>();
 
	public InShakearoundUserShakeEvent(String toUserName, String fromUserName, Integer createTime, String msgType) {
		super(toUserName, fromUserName, createTime, msgType);
	}
 
	public String getEvent() {
		return event;
	}
 
	public void setEvent(String event) {
		this.event = event;
	}
 
	public String getUuid() {
		return uuid;
	}
 
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
 
	public Integer getMajor() {
		return major;
	}
 
	public void setMajor(Integer major) {
		this.major = major;
	}
 
	public Integer getMinor() {
		return minor;
	}
 
	public void setMinor(Integer minor) {
		this.minor = minor;
	}
 
	public Float getDistance() {
		return distance;
	}
 
	public void setDistance(Float distance) {
		this.distance = distance;
	}
 
	public List<AroundBeacon> getAroundBeaconList() {
		return aroundBeaconList;
	}

	public void setAroundBeaconList(List<AroundBeacon> aroundBeaconList) {
		this.aroundBeaconList = aroundBeaconList;
	}

	public static class AroundBeacon {
	    private String uuid;
	    private Integer major;
	    private Integer minor;
	    private Float distance;//设备与用户的距离（浮点数；单位：米）
	 
	    public String getUuid() {
	        return uuid;
	    }
	 
	    public void setUuid(String uuid) {
	        this.uuid = uuid;
	    }
	 
	    public Integer getMajor() {
	        return major;
	    }
	 
	    public void setMajor(Integer major) {
	        this.major = major;
	    }
	 
	    public Integer getMinor() {
	        return minor;
	    }
	 
	    public void setMinor(Integer minor) {
	        this.minor = minor;
	    }
	 
	    public Float getDistance() {
	        return distance;
	    }
	 
	    public void setDistance(Float distance) {
	        this.distance = distance;
	    }
	}
}
