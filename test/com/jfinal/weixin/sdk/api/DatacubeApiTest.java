package com.jfinal.weixin.sdk.api;

import java.io.IOException;

public class DatacubeApiTest {

	public static void main(String[] args) throws IOException {
		AccessTokenApiTest.main(args);
		
		String begin_date = "2015-11-06";
		String end_date = "2015-11-06";
		
		System.out.println(DatacubeApi.getUserCumulate(begin_date, end_date));
		System.out.println(DatacubeApi.getUserSummary(begin_date, end_date));
		
		System.out.println(DatacubeApi.getArticleSummary(begin_date, end_date));
		System.out.println(DatacubeApi.getArticlEtotal(begin_date, end_date));
		
		System.out.println(DatacubeApi.getUserCumulate(begin_date, end_date));
		System.out.println(DatacubeApi.getUserSummary(begin_date, end_date));
		
		System.out.println(DatacubeApi.getUserRead(begin_date, end_date));
		System.out.println(DatacubeApi.getUserReadHour(begin_date, end_date));
		
		System.out.println(DatacubeApi.getUserShare(begin_date, end_date));
		System.out.println(DatacubeApi.getUserShareHour(begin_date, end_date));
	}
}
