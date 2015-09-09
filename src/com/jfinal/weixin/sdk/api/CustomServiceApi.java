/**
 * Copyright (c) 2011-2015, Unas 小强哥 (unas@qq.com).
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.kit.HttpKit;
import com.jfinal.weixin.sdk.utils.JsonUtils;

/**
 * 多客服功能</br>
 * 仅支持获取客服聊天记录接口，其他功能可以使用微信官方的多客服客户端软件来完成。
 * 
 * 客服接口：http://mp.weixin.qq.com/wiki/1/70a29afed17f56d537c833f89be979c9.html
 */
public class CustomServiceApi {

    private static String getRecordUrl = "https://api.weixin.qq.com/customservice/msgrecord/getrecord?access_token=";

    /**
     * 获取客服聊天记录
     */
    public static ApiResult getRecord(String jsonStr) {
        String jsonResult = HttpKit.post(getRecordUrl + AccessTokenApi.getAccessToken().getAccessToken(), jsonStr);
        return new ApiResult(jsonResult);
    }

    private static String customMessageUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";

    /**
     * 发送客服消息
     * @param message
     * @return ApiResult
     */
    private static ApiResult sendMsg(Map<String, Object> message) {
        String accessToken = AccessTokenApi.getAccessToken().getAccessToken();
        String jsonResult = HttpKit.post(customMessageUrl + accessToken, JsonUtils.toJson(message));
        return new ApiResult(jsonResult);
    }

    /**
     * 发送文本客服消息
     * @param openId
     * @param text
     */
    public static ApiResult sendText(String openId, String text) {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("touser", openId);
        json.put("msgtype", "text");

        Map<String, Object> textObj = new HashMap<String, Object>();
        textObj.put("content", text);

        json.put("text", textObj);
        return sendMsg(json);
    }

    /**
     * 发送图片消息
     * @param openId
     * @param media_id
     * @return
     */
    public static ApiResult sendImage(String openId, String media_id) {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("touser", openId);
        json.put("msgtype", "image");

        Map<String, Object> image = new HashMap<String, Object>();
        image.put("media_id", media_id);

        json.put("image", image);
        return sendMsg(json);
    }

    /**
     * 发送语言回复
     * @param openId
     * @param media_id
     * @return
     */
    public static ApiResult sendVoice(String openId, String media_id) {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("touser", openId);
        json.put("msgtype", "voice");

        Map<String, Object> voice = new HashMap<String, Object>();
        voice.put("media_id", media_id);

        json.put("voice", voice);
        return sendMsg(json);
    }

    /**
     * 发送视频回复
     * @param openId
     * @param media_id
     * @param title
     * @param description
     * @return
     */
    public static ApiResult sendVideo(String openId, String media_id, String title, String description) {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("touser", openId);
        json.put("msgtype", "video");

        Map<String, Object> video = new HashMap<String, Object>();
        video.put("media_id", media_id);
        video.put("title", title);
        video.put("description", description);

        json.put("video", video);
        return sendMsg(json);
    }

    /**
     * 发送音乐回复
     * @param openId
     * @param musicurl
     * @param hqmusicurl
     * @param thumb_media_id
     * @param title
     * @param description
     * @return
     */
    public static ApiResult sendMusic(String openId, String musicurl, String hqmusicurl, String thumb_media_id, String title, String description) {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("touser", openId);
        json.put("msgtype", "music");

        Map<String, Object> music = new HashMap<String, Object>();
        music.put("musicurl", musicurl);
        music.put("hqmusicurl", hqmusicurl);
        music.put("thumb_media_id", thumb_media_id);
        music.put("title", title);
        music.put("description", description);

        json.put("music", music);
        return sendMsg(json);
    }

    /**
     * 发送图文回复
     * @param openId
     * @param articles
     * @return
     */
    public static ApiResult sendNews(String openId, List<Articles> articles) {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("touser", openId);
        json.put("msgtype", "news");

        Map<String, Object> news = new HashMap<String, Object>();
        news.put("articles", articles);

        json.put("news", news);
        return sendMsg(json);
    }

    /**
     * 客户消息图文封装和 `News` 又略微区别，无法公用
     */
    public static class Articles {
        private String title;
        private String description;
        private String url;
        private String picurl;

        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
        public String getUrl() {
            return url;
        }
        public void setUrl(String url) {
            this.url = url;
        }
        public String getPicurl() {
            return picurl;
        }
        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }
    }

    /**
     * 发送卡券
     * @param openId
     * @param card_id
     * @param card_ext 详情及签名规则: http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html#.E9.99.84.E5.BD.954-.E5.8D.A1.E5.88.B8.E6.89.A9.E5.B1.95.E5.AD.97.E6.AE.B5.E5.8F.8A.E7.AD.BE.E5.90.8D.E7.94.9F.E6.88.90.E7.AE.97.E6.B3.95
     * @return
     */
    public static ApiResult sendCoupon(String openId, String card_id, String card_ext) {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("touser", openId);
        json.put("msgtype", "wxcard");

        Map<String, Object> wxcard = new HashMap<String, Object>();
        wxcard.put("card_id", card_id);
        wxcard.put("card_ext", card_ext);

        json.put("wxcard", wxcard);
        return sendMsg(json);
    }

}
