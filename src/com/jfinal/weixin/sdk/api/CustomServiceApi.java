/**
 * Copyright (c) 2011-2015, Unas 小强哥 (unas@qq.com).
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.api;

import com.jfinal.kit.HttpKit;

/**
 * 多客服功能</br>
 * 仅支持获取客服聊天记录接口，其他功能可以使用微信官方的多客服客户端软件来完成。
 */
public class CustomServiceApi
{
    private static String getRecordUrl = "https://api.weixin.qq.com/customservice/msgrecord/getrecord?access_token=";

    /**
     * 获取客服聊天记录
     */
    public static ApiResult getRecord(String jsonStr)
    {
        String jsonResult = HttpKit.post(getRecordUrl + AccessTokenApi.getAccessToken().getAccessToken(), jsonStr);
        return new ApiResult(jsonResult);
    }
}
