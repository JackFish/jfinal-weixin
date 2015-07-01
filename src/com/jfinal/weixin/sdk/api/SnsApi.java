/**
 * Copyright (c) 2011-2015, Unas 小强哥 (unas@qq.com).
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.api;

import com.jfinal.kit.HttpKit;
import com.jfinal.weixin.sdk.kit.ParaMap;

/**
 * 网页授权获取用户基本信息 API
 */
public class SnsApi
{
    private static String getUserInfo = "https://api.weixin.qq.com/sns/userinfo";

    public static ApiResult getUserInfo(String code, String openId)
    {
        ParaMap pm = ParaMap.create("access_token", SnsAccessTokenApi.getAccessToken(code).getAccessToken()).put("openid", openId).put("lang", "zh_CN");
        return new ApiResult(HttpKit.get(getUserInfo, pm.getData()));
    }
}
