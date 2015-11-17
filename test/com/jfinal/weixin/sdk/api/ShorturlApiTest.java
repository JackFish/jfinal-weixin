package com.jfinal.weixin.sdk.api;

import java.io.IOException;

public class ShorturlApiTest {

	public static void main(String[] args) throws IOException {
		AccessTokenApiTest.main(args);
		
		ApiResult ar1 = ShorturlApi.getShortUrl("http://www.jfinal.com/download?file=jfinal-2.0-manual.pdf");
		
		System.out.println(ar1.getJson());
	}
}
