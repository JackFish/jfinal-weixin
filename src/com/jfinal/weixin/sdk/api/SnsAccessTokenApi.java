/**
 * Copyright (c) 2011-2015, Unas 小强哥 (unas@qq.com).
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.api;

import com.jfinal.kit.HttpKit;
import com.jfinal.weixin.sdk.kit.ParaMap;

import java.util.Map;

/**
 * 网页授权获取 access_token API
 */
public class SnsAccessTokenApi
{
    private static String url = "https://api.weixin.qq.com/sns/oauth2/access_token?grant_type=authorization_code";

    /**
     * 通过code获取access_token
     *
     * @param code   第一步获取的code参数
     * @param appId  应用唯一标识
     * @param secret 应用密钥AppSecret
     * @return SnsAccessToken
     */
    public static SnsAccessToken getSnsAccessToken(String appId, String secret, String code)
    {
        SnsAccessToken result = null;
        for (int i = 0; i < 3; i++)
        {    // 最多三次请求
            Map<String, String> queryParas = ParaMap.create("appid", appId).put("secret", secret).put("code", code).getData();
            String json = HttpKit.get(url, queryParas);
            result = new SnsAccessToken(json);

            if (result.isAvailable())
                break;
        }
        return result;
    }
}
