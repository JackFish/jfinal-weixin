/**
 * Copyright (c) 2011-2015, Unas 小强哥 (unas@qq.com).
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.api;

import java.util.Map;

import com.jfinal.weixin.sdk.utils.JsonUtils;


/**
 * SnsAccessToken
 * 封装 access_token
 */
public class SnsAccessToken
{

    private String access_token;    // 正确获取到 access_token 时有值
    private Integer expires_in;        // 正确获取到 access_token 时有值
    private String refresh_token;    //
    private String openid;    //
    private String scope;    //
    private String unionid;    //
    private Integer errcode;        // 出错时有值
    private String errmsg;            // 出错时有值

    private Long expiredTime;        // 正确获取到 access_token 时有值，存放过期时间
    private String json;

    public SnsAccessToken(String jsonStr)
    {
        this.json = jsonStr;

        try
        {
            @SuppressWarnings("unchecked")
            Map<String, Object> temp = JsonUtils.decode(jsonStr, Map.class);
            access_token = (String) temp.get("access_token");
            expires_in = (Integer) temp.get("expires_in");
            refresh_token = (String) temp.get("refresh_token");
            openid = (String) temp.get("openid");
            unionid = (String) temp.get("unionid");
            scope = (String) temp.get("scope");
            errcode = (Integer) temp.get("errcode");
            errmsg = (String) temp.get("errmsg");

            if (expires_in != null)
                expiredTime = System.currentTimeMillis() + ((expires_in - 5) * 1000);

        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public String getJson()
    {
        return json;
    }

    public boolean isAvailable()
    {
        if (expiredTime == null)
            return false;
        if (errcode != null)
            return false;
        if (expiredTime < System.currentTimeMillis())
            return false;
        return access_token != null;
    }

    public String getAccessToken()
    {
        return access_token;
    }

    public Integer getExpiresIn()
    {
        return expires_in;
    }

    public String getRefresh_token()
    {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token)
    {
        this.refresh_token = refresh_token;
    }

    public String getOpenid()
    {
        return openid;
    }

    public void setOpenid(String openid)
    {
        this.openid = openid;
    }

    public String getScope()
    {
        return scope;
    }

    public void setScope(String scope)
    {
        this.scope = scope;
    }

    public Integer getErrorCode()
    {
        return errcode;
    }

    public String getErrorMsg()
    {
        if (errcode != null)
        {
            String result = ReturnCode.get(errcode);
            if (result != null)
                return result;
        }
        return errmsg;
    }

    public String getUnionid()
    {
        return unionid;
    }

    public void setUnionid(String unionid)
    {
        this.unionid = unionid;
    }
}
