<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>JFinal-weixin支付测试</title>
	</head>
<body>
	JFinal-weixin支付测试<br>
	微信支付v3官方文档地址：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=7_7&index=6
</body>
<script type="text/javascript">
	function onBridgeReady(){
		WeixinJSBridge.invoke(
			'getBrandWCPayRequest', 
			${json},
			function(res){
				// 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
				if(res.err_msg == "get_brand_wcpay_request：ok" ) {
					
				}
			}
		); 
	}
	if (typeof WeixinJSBridge == "undefined"){
		if( document.addEventListener ){
			document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
		}else if (document.attachEvent){
			document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
			document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
		}
	}else{
		onBridgeReady();
	}
</script>
</html>