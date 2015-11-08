package com.jfinal.weixin.sdk.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jfinal.weixin.sdk.api.MediaApi.MediaType;

public class MediaApiTest {

	@Before
	public void setUp() {
		AccessTokenApiTest.init();
	}
	
	/**
	 * 测试获取素材总数
	 */
	@Test
	public void testGetMaterialCount() {
		ApiResult ar = MediaApi.getMaterialCount();
		
		String json = ar.getJson();
		System.out.println("testGetMaterialCount: " + json);
		Assert.assertNotNull(json);
	}
	
	/**
	 * 测试获取素材列表
	 */
	@Test
	public void testBatchGetMaterial() {
		ApiResult ar = MediaApi.batchGetMaterial(MediaType.image, 1, 10);
		
		String json = ar.getJson();
		System.out.println("testBatchGetMaterial: " + json);
		Assert.assertNotNull(json);
	}
	
}
