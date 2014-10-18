package com.jfinal.weixin.demo;

import com.jfinal.core.Controller;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.MenuApi;
import com.jfinal.weixin.sdk.api.UserApi;

public class ApiController extends Controller {
	
	/*
	public void index() {
		render("/api/index.html");
	}
	*/
	
	public void getMenu() {
		ApiResult apiResult = MenuApi.getMenu();
		if (apiResult.isSucceed())
			renderText(apiResult.getJson());
		else
			renderText(apiResult.getErrorMsg());
	}
	
	public void getFollowers() {
		ApiResult apiResult = UserApi.getFollows();
		renderText(apiResult.getJson());
	}
}




