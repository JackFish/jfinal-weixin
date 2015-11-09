package com.jfinal.weixin.sdk.api;

import java.io.File;
import java.io.IOException;

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
	
	public static void main(String[] args) throws IOException {
		AccessTokenApiTest.init();
		
//		ApiResult ar = MediaApi.uploadMedia(MediaType.image, new File("/Users/lcm/Desktop/IMG_3491.JPG"));
//		System.out.println(ar.getJson());
//		{"type":"image","media_id":"RE3pVYtzjQmpCv5cdNt_5vylRqR58w22-QzfTKBE64JIJOAqNiCSocniTjB91jRP","created_at":1447079025}

		System.out.println(MediaApi.uploadImg(new File("/Users/lcm/Desktop/IMG_3491.JPG")));
//		{"url":"http:\/\/mmbiz.qpic.cn\/mmbiz\/z8E5xcHa5mPTjoruHNbrvc61rkBSFcPpm4ZnGYHoQaI330Pb4AzDncWyR08y3Uj0FQfujE2fcxnnrAtM4q6iavQ\/0"}

	}
}
