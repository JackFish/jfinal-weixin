package com.jfinal.weixin.sdk.utils;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonTest {

	static Gson gson = new GsonBuilder().create();
	
	public static void main(String[] args) {
		int id = 111;
		String name = "张三疯";
		
		Map<String, Map<String, Object>> groupData = new HashMap<String, Map<String, Object>>();
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("id", id);
		mapData.put("name", name);
		groupData.put("group", mapData);
		
		System.out.println(gson.toJson(groupData));
	}
}
