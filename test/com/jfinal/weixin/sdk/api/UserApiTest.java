package com.jfinal.weixin.sdk.api;

import java.io.IOException;

public class UserApiTest {

	public static void main(String[] args) throws IOException {
		AccessTokenApiTest.main(args);
		
		ApiResult ar = UserApi.getFollows();
		System.out.println(ar.getJson());
	}
}
