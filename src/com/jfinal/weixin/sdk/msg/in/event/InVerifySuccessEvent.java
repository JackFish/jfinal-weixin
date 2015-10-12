/**
 * Copyright (c) 2011-2015, Unas 小强哥 (unas@qq.com).
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.msg.in.event;

/**
 <xml><ToUserName><![CDATA[toUser]]></ToUserName>
 <FromUserName><![CDATA[FromUser]]></FromUserName>
 <CreateTime>1442401156</CreateTime>
 <MsgType><![CDATA[event]]></MsgType>
 <Event><![CDATA[qualification_verify_success]]></Event>
 <ExpiredTime>1442401156</ExpiredTime>
 </xml>
 */
public class InVerifySuccessEvent extends EventInMsg
{
    //资质认证成功
    public static final String EVENT_IN_QUALIFICATION_VERIFY_SUCCESS = "qualification_verify_success";
    //名称认证成功
    public static final String EVENT_IN_NAMING_VERIFY_SUCCESS = "naming_verify_success";
    //年审通知
    public static final String EVENT_IN_ANNUAL_RENEW = "annual_renew";
    //认证过期失效通知
    public static final String EVENT_IN_VERIFY_EXPIRED = "verify_expired";

    private String expiredTime;

    public InVerifySuccessEvent(String toUserName, String fromUserName, Integer createTime, String msgType, String event)
    {
        super(toUserName, fromUserName, createTime, msgType, event);
    }

    public String getExpiredTime()
    {
        return expiredTime;
    }

    public void setExpiredTime(String expiredTime)
    {
        this.expiredTime = expiredTime;
    }
}






