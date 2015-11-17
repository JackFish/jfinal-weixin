package com.jfinal.weixin.sdk.api;

import java.io.IOException;

public class QrcodeApiTest {

	public static void main(String[] args) throws IOException {
		AccessTokenApiTest.main(args);
		
		ApiResult ar1 = QrcodeApi.createTemporary(1000, 1234);
		
		System.out.println(ar1.getJson());
		
		ApiResult ar2 = QrcodeApi.createPermanent(1234);
		
		System.out.println(ar2.getJson());
		
		ApiResult ar3 = QrcodeApi.createPermanent("2345");
		
		System.out.println(ar3.getJson());
	}
}
