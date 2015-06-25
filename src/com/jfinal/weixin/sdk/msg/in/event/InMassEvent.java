/**
 * Copyright (c) 2011-2015, Unas 小强哥 (unas@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.msg.in.event;

import com.jfinal.weixin.sdk.msg.in.InMsg;

/**
 * 由于群发任务提交后，群发任务可能在一定时间后才完成，因此，群发接口调用时，仅会给出群发任务是否提交成功的提示，
 * 若群发任务提交成功，则在群发任务结束时，会向开发者在公众平台填写的开发者URL（callback URL）推送事件。
 *
	<xml>
		<ToUserName><![CDATA[gh_7f083739789a]]></ToUserName>
		<FromUserName><![CDATA[oia2TjuEGTNoeX76QEjQNrcURxG8]]></FromUserName>
		<CreateTime>1395658920</CreateTime>
		<MsgType><![CDATA[event]]></MsgType>
			<Event><![CDATA[MASSSENDJOBFINISH]]></Event>
			<MsgID>1988</MsgID>
			<Status><![CDATA[sendsuccess]]></Status>
            <TotalCount>100</TotalCount>
            <FilterCount>80</FilterCount>
            <SentCount>75</SentCount>
            <ErrorCount>5</ErrorCount>
	</xml>
 */
public class InMassEvent extends InMsg {

	// 推送群发结果：MASSSENDJOBFINISH
	private String event;
	private String msgId;
	private String status;
	private String totalCount;
    private String filterCount;
	private String sentCount;
	private String errorCount;

	public InMassEvent(String toUserName, String fromUserName, Integer createTime, String msgType) {
		super(toUserName, fromUserName, createTime, msgType);
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

    public String getTotalCount()
    {
        return totalCount;
    }

    public void setTotalCount(String totalCount)
    {
        this.totalCount = totalCount;
    }

    public String getFilterCount()
    {
        return filterCount;
    }

    public void setFilterCount(String filterCount)
    {
        this.filterCount = filterCount;
    }

    public String getSentCount()
    {
        return sentCount;
    }

    public void setSentCount(String sentCount)
    {
        this.sentCount = sentCount;
    }

    public String getErrorCount()
    {
        return errorCount;
    }

    public void setErrorCount(String errorCount)
    {
        this.errorCount = errorCount;
    }
}

