package com.jfinal.weixin.sdk.api;

import java.io.IOException;

public class DatacubeApiTest {

	public static void main(String[] args) throws IOException {
		AccessTokenApiTest.main(args);
		
		String begin_date = "2015-11-06";
		String end_date = "2015-11-06";
		
		// 获取用户增减数据（getusersummary）,最大时间跨度：7天
		System.out.println(DatacubeApi.getUserCumulate(begin_date, end_date));

		// 获取累计用户数据（getusercumulate）,最大时间跨度：7天
		System.out.println(DatacubeApi.getUserSummary(begin_date, end_date));

		/**
		 * 获取图文群发每日数据，最大跨度1天
		 */
		System.out.println(DatacubeApi.getArticleSummary(begin_date, end_date));

		/**
		 * 获取图文群发总数据，最大跨度1天
		 */
		System.out.println(DatacubeApi.getArticlEtotal(begin_date, end_date));

		/**
		 * 获取图文统计数据，最大跨度3天
		 */
		System.out.println(DatacubeApi.getUserRead(begin_date, end_date));

		/**
		 * 获取图文统计分时数据，最大跨度1天
		 */
		System.out.println(DatacubeApi.getUserReadHour(begin_date, end_date));

		/**
		 * 获取图文分享转发数据，最大跨度7天
		 */
		System.out.println(DatacubeApi.getUserShare(begin_date, end_date));

		/**
		 * 获取图文分享转发分时数据，最大跨度1天

		 */
		System.out.println(DatacubeApi.getUserShareHour(begin_date, end_date));

		/**
		 * 获取消息发送概况数据，最大跨度7天
		 */
		System.out.println(DatacubeApi.getUpStreamMsg(begin_date, end_date));

		/**
		 * 获取消息分送分时数据，最大跨度1天
		 */
		System.out.println(DatacubeApi.getUpStreamMsgHour(begin_date, end_date));

		/**
		 * 获取消息发送周数据，最大跨度30天
		 */
		System.out.println(DatacubeApi.getUpStreamMsgWeekMsg(begin_date, end_date));

		/**
		 * 获取消息发送月数据，最大跨度30天
		 */
		System.out.println(DatacubeApi.getUpStreamMsgMonth(begin_date, end_date));

		/**
		 * 获取消息发送分布数据，最大跨度15天
		 */
		System.out.println(DatacubeApi.getUpStreamMsgDist(begin_date, end_date));

		/**
		 * 获取消息发送分布周数据，最大跨度30天
		 */
		System.out.println(DatacubeApi.getUpStreamMsgDistWeek(begin_date, end_date));
		/**
		 * 获取消息发送分布月数据，最大跨度30天
		 */
		System.out.println(DatacubeApi.getUpStreamMsgDistMonth(begin_date, end_date));

		/**
		 * 获取接口分析数据，最大跨度30天
		 */
		System.out.println(DatacubeApi.getInterFaceSummary(begin_date, end_date));

		/**
		 * 获取接口分析分时数据，最大跨度1天
		 */
		System.out.println(DatacubeApi.getInterFaceSummaryHour(begin_date, end_date));

	}
}
