/**
 * Copyright (c) 2011-2015, Unas 小强哥 (unas@qq.com).
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.api;

import com.jfinal.kit.HttpKit;
import com.jfinal.weixin.sdk.kit.ParaMap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 网页授权获取 access_token API
 */
public class SnsAccessTokenApi
{

    // https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
    private static String url = "https://api.weixin.qq.com/sns/oauth2/access_token?grant_type=authorization_code";

    // 利用 appId 与 accessToken 建立关联，支持多账户
    private static Map<String, AccessToken> map = new ConcurrentHashMap<String, AccessToken>();    // private static AccessToken accessToken;

    /**
     * 从缓存中获取 access token，如果未取到或者 access token 不可用则先更新再获取
     */
    public static AccessToken getAccessToken(String code)
    {
        String appId = ApiConfigKit.getApiConfig().getAppId();
        AccessToken result = map.get(appId);
        if (result != null && result.isAvailable())
            return result;

        refreshAccessToken(code);
        return map.get(appId);
    }

    /**
     * 强制更新 access token 值
     */
    public static synchronized void refreshAccessToken(String code)
    {
        ApiConfig ac = ApiConfigKit.getApiConfig();
        AccessToken result = null;
        for (int i = 0; i < 3; i++)
        {    // 最多三次请求
            String appId = ac.getAppId();
            String appSecret = ac.getAppSecret();
            Map<String, String> queryParas = ParaMap.create("appid", appId).put("secret", appSecret).put("code", code).getData();
            String json = HttpKit.get(url, queryParas);
            result = new AccessToken(json);

            if (result.isAvailable())
                break;
        }

        // 三次请求如果仍然返回了不可用的 access token 仍然 put 进去，便于上层通过 AccessToken 中的属性判断底层的情况
        map.put(ac.getAppId(), result);
    }
}
