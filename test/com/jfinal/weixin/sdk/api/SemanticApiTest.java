package com.jfinal.weixin.sdk.api;

import java.io.IOException;

public class SemanticApiTest {

	public static void main(String[] args) throws IOException {
		AccessTokenApiTest.main(args);
		
		String jsonStr = "{'query':'查一下明天从北京到上海的南航机票','city':'北京','category':'flight,hotel','appid':'wx9803d1188fa5fbda','uid':'123456'}";
		
		System.out.println(SemanticApi.search(jsonStr));
	}
}
