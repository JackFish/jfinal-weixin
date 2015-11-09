package com.jfinal.weixin.sdk.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.kit.HttpKit;
import com.jfinal.weixin.sdk.utils.JsonUtils;

/**
 * 分组Api
 * @author L.cn
 * 文档地址：http://mp.weixin.qq.com/wiki/5/0d8acdd6d4433c877fbea938a2f133cd.html
 */
public class GroupsApi {

	private static String createUrl = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=";
	
	/**
	 * 创建分组，一个公众账号，最多支持创建100个分组。
	 * @param name 分组名
	 * @return ApiResult
	 */
	public static ApiResult create(String name) {
		String url = createUrl + AccessTokenApi.getAccessTokenStr();
		
		Map<String, Map<String, String>> groupData = new HashMap<String, Map<String, String>>();
		Map<String, String> mapData = new HashMap<String, String>();
		mapData.put("name", name);
		groupData.put("group", mapData);
		
		String jsonResult = HttpKit.post(url, JsonUtils.toJson(groupData));
		return new ApiResult(jsonResult);
	}
	
	private static String getUrl = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=";
	
	/**
	 * 查询所有分组
	 * @return ApiResult
	 */
	public static ApiResult get() {
		String url = getUrl + AccessTokenApi.getAccessTokenStr();
		
		String jsonResult = HttpKit.get(url);
		return new ApiResult(jsonResult);
	}
	
	private static String getIdUrl = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=";
	
	/**
	 * 通过用户的OpenID查询其所在的GroupID
	 * @param openid 普通用户的标识，对当前开发者帐号唯一
	 * @return ApiResult
	 */
	public static ApiResult getId(String openid) {
		String url = getIdUrl + AccessTokenApi.getAccessTokenStr();
		
		Map<String, String> mapData = new HashMap<String, String>();
		mapData.put("openid", openid);
		
		String jsonResult = HttpKit.post(url, JsonUtils.toJson(mapData));
		return new ApiResult(jsonResult);
	}
	
	private static String updateUrl = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=";
	
	/**
	 * 修改分组名
	 * @param id 分组id，由微信分配
	 * @param name 分组名字（30个字符以内）
	 * @return ApiResult
	 */
	public static ApiResult update(int id, String name) {
		String url = updateUrl + AccessTokenApi.getAccessTokenStr();
		
		Map<String, Map<String, Object>> groupData = new HashMap<String, Map<String, Object>>();
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("id", id);
		mapData.put("name", name);
		groupData.put("group", mapData);
		
		String jsonResult = HttpKit.post(url, JsonUtils.toJson(groupData));
		return new ApiResult(jsonResult);
	}
	
	private static String membersUpdateUrl = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=";
	
	/**
	 * 移动用户分组
	 * @param openid 用户唯一标识符
	 * @param to_groupid 分组id
	 * @return ApiResult
	 */
	public static ApiResult membersUpdate(String openid, int to_groupid) {
		String url = membersUpdateUrl + AccessTokenApi.getAccessTokenStr();
		
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("openid", openid);
		mapData.put("to_groupid", to_groupid);
		
		String jsonResult = HttpKit.post(url, JsonUtils.toJson(mapData));
		return new ApiResult(jsonResult);
	}
	
	private static String membersBatchUpdateUrl = "https://api.weixin.qq.com/cgi-bin/groups/members/batchupdate?access_token=";
	
	/**
	 * 批量移动用户分组
	 * @param openidList 用户唯一标识符openid的列表（size不能超过50）
	 * @param to_groupid 分组id
	 * @return ApiResult
	 */
	public static ApiResult membersBatchUpdate(List<String> openidList, int to_groupid) {
		String url = membersBatchUpdateUrl + AccessTokenApi.getAccessTokenStr();
		
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("openid_list", openidList);
		mapData.put("to_groupid", to_groupid);
		
		String jsonResult = HttpKit.post(url, JsonUtils.toJson(mapData));
		return new ApiResult(jsonResult);
	}
	
	private static String deleteUrl = "https://api.weixin.qq.com/cgi-bin/groups/delete?access_token=";
	
	/**
	 * 删除分组
	 * @param id 分组的id
	 * @return ApiResult
	 */
	public static ApiResult delete(int id) {
		String url = deleteUrl + AccessTokenApi.getAccessTokenStr();
		
		Map<String, Map<String, Object>> groupData = new HashMap<String, Map<String, Object>>();
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("id", id);
		groupData.put("group", mapData);
		
		String jsonResult = HttpKit.post(url, JsonUtils.toJson(mapData));
		return new ApiResult(jsonResult);
	}
	
}
