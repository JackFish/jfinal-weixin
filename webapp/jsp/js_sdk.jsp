<%--
注意：所有的JS接口只能在公众号绑定的域名下调用，公众号开发者需要先登录微信公众平台进入“公众号设置”的“功能设置”里填写“JS接口安全域名”。
如果发现在 Android 不能分享自定义内容，请到官网下载最新的包覆盖安装，Android 自定义分享接口需升级至 6.0.2.58 版本及以上。
完整 JS-SDK 文档地址：http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html

使用方法：
需要调用的地方加入下面的内容
//-----------------------------------------------------------------------------//
//下面为配置的覆盖，应该考虑更优的参数覆盖方式
<script type="text/javascript">
var shareData = {
    title: shareTitle,
    desc: config.content,
    link: shareURL,
    imgUrl: shareIMG
};
</script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="http://xxxx/js_sdk.jsp"></script>
//-----------------------------------------------------------------------------//
 --%>
<%@page import="com.jfinal.kit.PropKit"%>
<%@page import="com.jfinal.weixin.sdk.api.ApiConfigKit"%>
<%@page import="com.jfinal.weixin.sdk.api.ApiConfig"%>
<%@page import="com.jfinal.kit.HashKit"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.UUID"%>
<%@page import="java.util.TreeMap"%>
<%@page import="com.jfinal.weixin.sdk.api.JsTicket"%>
<%@page import="com.jfinal.weixin.sdk.api.JsTicketApi.JsApiType"%>
<%@page import="com.jfinal.weixin.sdk.api.JsTicketApi"%>
<%@page import="com.jfinal.kit.StrKit"%>
<%@ page language="java" contentType="application/x-javascript; charset=utf-8" isELIgnored="false"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%--微信环境内展示 --%>
<%
// 1.拼接url（当前网页的URL，不包含#及其后面部分）
String _wxShareUrl = request.getHeader("Referer");
if (StrKit.notBlank(_wxShareUrl)) {
    _wxShareUrl = _wxShareUrl.split("#")[0];
} else {
    return;
}

String appId = PropKit.get("appId");
String appSecret = PropKit.get("appSecret");
ApiConfig ac = new ApiConfig();
ac.setAppId(appId);
ac.setAppSecret(appSecret);

ApiConfigKit.setThreadLocalApiConfig(ac);
String _wxJsapiTicket = "";
try {
    JsTicket jsTicket = JsTicketApi.getTicket(JsApiType.jsapi);
    _wxJsapiTicket      = jsTicket.getTicket();
} finally {
    ApiConfigKit.removeThreadLocalApiConfig();
}

Map<String, String> _wxMap = new TreeMap<String, String>();
//noncestr
String _wxNoncestr         = UUID.randomUUID().toString().replace("-", "");
//timestamp
String _wxTimestamp        = (System.currentTimeMillis() / 1000) + "";

//参数封装
_wxMap.put("noncestr", _wxNoncestr);
_wxMap.put("timestamp", _wxTimestamp );
_wxMap.put("jsapi_ticket", _wxJsapiTicket);
_wxMap.put("url", _wxShareUrl);

// 加密获取signature
StringBuilder _wxBaseString = new StringBuilder();
for (Entry<String, String> param : _wxMap.entrySet()) {
    _wxBaseString.append(param.getKey()).append("=").append(param.getValue()).append("&");
}
String _wxSignString = _wxBaseString.substring(0, _wxBaseString.length() - 1);
// signature
String _wxSignature = HashKit.sha1(_wxSignString);
 %>
<%--兼容新老版本 --%>
wx.config({
    debug: false,
    appId: '<%=appId%>',
    timestamp: '<%=_wxTimestamp %>',
    nonceStr:  '<%=_wxNoncestr  %>',
    signature: '<%=_wxSignature %>',
    jsApiList: [
        'checkJsApi',
        'onMenuShareTimeline',
        'onMenuShareAppMessage',
        'onMenuShareQQ',
        'onMenuShareWeibo',
        'hideMenuItems',
        'showMenuItems',
        'hideAllNonBaseMenuItem',
        'showAllNonBaseMenuItem',
        'translateVoice',
        'startRecord',
        'stopRecord',
        'onRecordEnd',
        'playVoice',
        'pauseVoice',
        'stopVoice',
        'uploadVoice',
        'downloadVoice',
        'chooseImage',
        'previewImage',
        'uploadImage',
        'downloadImage',
        'getNetworkType',
        'openLocation',
        'getLocation',
        'hideOptionMenu',
        'showOptionMenu',
        'closeWindow',
        'scanQRCode',
        'chooseWXPay',
        'openProductSpecificView',
        'addCard',
        'chooseCard',
        'openCard'
    ]
});

<%--
wx.error(function (res) {
    alert(res.errMsg);
});
--%>
<%-- 默认分享数据 --%>
var shareData = typeof(shareData) === 'undefined' ? {
    title: '微信JS-SDK Demo测试',
    desc: '微信JS-SDK,帮助第三方为用户提供更优质的移动web服务',
    link: 'http://www.jfinal.com/',
    imgUrl: 'http://www.jfinal.com/img/weixin_142X142.jpg'
} : shareData;

<%--新版微信--%>
wx.ready(function () {
    wx.onMenuShareAppMessage(shareData);
    wx.onMenuShareTimeline(shareData);
    wx.onMenuShareWeibo(shareData);
    wx.onMenuShareQQ(shareData);
});
<%--老版微信--%>
document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
    WeixinJSBridge.on('menu:share:appmessage', function(argv){
          WeixinJSBridge.invoke('sendAppMessage',{
            "appid": "wx6eb49a04d0f504a8",
            "img_url": shareData.imgUrl,
            "img_width": "640",
            "img_height": "640",
            "link": shareData.link,
            "desc": shareData.desc,
            "title": shareData.title
        }, function(res) {});
    });
    WeixinJSBridge.on('menu:share:timeline', function(argv){
        WeixinJSBridge.invoke('shareTimeline',{
            "img_url": shareData.imgUrl,
            "img_width": "640",
            "img_height": "640",
            "link": shareData.link,
            "desc": shareData.desc,
            "title": shareData.title
        }, function(res) {});
    });
}, false);