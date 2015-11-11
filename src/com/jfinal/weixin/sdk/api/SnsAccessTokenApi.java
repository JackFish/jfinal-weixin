/**
 * Copyright (c) 2011-2015, Unas 小强哥 (unas@qq.com).
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.api;

import com.jfinal.kit.HttpKit;
import com.jfinal.weixin.sdk.kit.ParaMap;
import com.jfinal.weixin.sdk.kit.PaymentKit;

import java.util.HashMap;
import java.util.Map;

/**
 * 网页授权获取 access_token API
 */
public class SnsAccessTokenApi
{
    private static String url = "https://api.weixin.qq.com/sns/oauth2/access_token?grant_type=authorization_code";
    private static String authorize_uri = "http://open.weixin.qq.com/connect/oauth2/authorize";
    
    /**
     * 生成Authorize链接
     * @param appId 应用id
     * @param redirect_uri 回跳地址
     * @param snsapiBase 时候完全信息
     * @return url
     */
    public static String getAuthorizeURL(String appId, String redirect_uri, boolean snsapiBase) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", appId);
        params.put("response_type", "code");
        params.put("redirect_uri", redirect_uri);
        // snsapi_base（不弹出授权页面，只能拿到用户openid）
        // snsapi_userinfo（弹出授权页面，这个可以通过 openid 拿到昵称、性别、所在地）
        if (snsapiBase) {
            params.put("scope", "snsapi_base");
        } else {
            params.put("scope", "snsapi_userinfo");
        }
        params.put("state", "wx#wechat_redirect");
        String para = PaymentKit.packageSign(params, false);
        return authorize_uri + "?" + para;
    }
    
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
