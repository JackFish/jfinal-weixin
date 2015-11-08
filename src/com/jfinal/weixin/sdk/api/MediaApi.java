package com.jfinal.weixin.sdk.api;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import com.jfinal.kit.HttpKit;
import com.jfinal.weixin.sdk.utils.JsonUtils;

/**
 * 素材管理，待完善
 * @author l.cm
 * 文档：http://mp.weixin.qq.com/wiki/5/963fc70b80dc75483a271298a76a8d59.html
 */
public class MediaApi {
	
	/**
	 * 上传的临时多媒体文件有格式
	 */
	public static enum MediaType {
		image, voice, video, thumb
	}
	
	// 新增临时素材
	private static String upload_url = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=";
	
	public static ApiResult uploadMedia(MediaType mediaType, File file) {
		String url = upload_url + AccessTokenApi.getAccessTokenStr() + "&type=" + mediaType.name();
		return null;
	}
	
	// 获取临时素材
	private static String get_url = "https://api.weixin.qq.com/cgi-bin/media/get?access_token={}&media_id={}";
	
	// 新增永久素材
	private static String add_news_url = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=ACCESS_TOKEN";
	// 获取永久素材
	private static String get_material_url = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=";
	
	/**
	 * 获取永久素材
	 * @param media_id 要获取的素材的media_id
	 * @return ApiResult 素材信息
	 */
	public static ApiResult getMaterial(String media_id) {
		String url = get_material_url + AccessTokenApi.getAccessTokenStr();
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("media_id", media_id);
		
		String jsonResult = HttpKit.post(url, JsonUtils.toJson(data));
		return new ApiResult(jsonResult);
	}
	
	// 下载素材
	private static MediaFile download(String url) {
		MediaFile mediaFile = new MediaFile();
//		HttpURLConnection conn = initConn(url, "get", null);
//		if(conn.getContentType().equalsIgnoreCase("text/plain")){
//			// 定义BufferedReader输入流来读取URL的响应  
//			InputStream in = conn.getInputStream();
//			BufferedReader read = new BufferedReader(new InputStreamReader(in, "utf-8"));
//			String valueString = null;
//			StringBuffer bufferRes = new StringBuffer();
//			while ((valueString = read.readLine()) != null){
//				bufferRes.append(valueString);
//			}
//			read.close();
//			in.close();
//			mediaFile.setError(bufferRes.toString());
//		}else{
//			BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());  
//			String ds = conn.getHeaderField("Content-disposition");
//			String fullName = ds.substring(ds.indexOf("filename=\"")+10,ds.length()-1);
//			String relName = fullName.substring(0, fullName.lastIndexOf("."));
//			String suffix = fullName.substring(relName.length()+1);
//
//			mediaFile.setFullName(fullName);
//			mediaFile.setFileName(relName);
//			mediaFile.setSuffix(suffix);
//			mediaFile.setContentLength(conn.getHeaderField("Content-Length"));
//			mediaFile.setContentType(conn.getHeaderField("Content-Type"));
//
//			mediaFile.setFileStream(bis);
//			bis.close();
//		}
		return mediaFile;
	}
	
	// 删除永久素材
	private static String del_material_url = "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=";
	
	/**
	 * 删除永久素材
	 * @param media_id 要获取的素材的media_id
	 * @return ApiResult 返回信息
	 */
	public static ApiResult delMaterial(String media_id) {
		String url = del_material_url + AccessTokenApi.getAccessTokenStr();
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("media_id", media_id);
		
		String jsonResult = HttpKit.post(url, JsonUtils.toJson(data));
		return new ApiResult(jsonResult);
	}
	
	// 修改永久图文素材
	private static String update_news_url = "https://api.weixin.qq.com/cgi-bin/material/update_news?access_token=";
	
	/**
	 * 修改永久图文素材
	 * @param media_id 要修改的图文消息的id
	 * @param index 要更新的文章在图文消息中的位置（多图文消息时，此字段才有意义），第一篇为0
	 * @param mediaArticles 图文素材
	 * @return ApiResult 返回信息
	 */
	public static ApiResult updateNews(String media_id, int index, MediaArticles mediaArticles) {
		String url = update_news_url + AccessTokenApi.getAccessTokenStr();
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("media_id", media_id);
		data.put("index", index);
		data.put("articles", mediaArticles);
		
		String jsonResult = HttpKit.post(url, JsonUtils.toJson(data));
		return new ApiResult(jsonResult);
	}
	
	// 获取素材总数
	private static String get_materialcount_url = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=";

	/**
	 * 获取素材总数
	 * @return ApiResult 返回信息
	 */
	public static ApiResult getMaterialCount() {
		String url = get_materialcount_url + AccessTokenApi.getAccessTokenStr();
		System.out.println(url);
		String jsonResult = HttpKit.get(url);
		return new ApiResult(jsonResult);
	}
	
	// 获取素材列表
	private static String batchget_material_url = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=";
	
	/**
	 * 获取素材列表
	 * @param mediaType 素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
	 * @param offset 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
	 * @param count 返回素材的数量，取值在1到20之间
	 * @return ApiResult 返回信息
	 */
	public static ApiResult batchGetMaterial(MediaType mediaType, int offset, int count) {
		String url = batchget_material_url + AccessTokenApi.getAccessTokenStr();
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("type", mediaType.name());
		data.put("offset", offset);
		data.put("count", count);
		
		String jsonResult = HttpKit.post(url, JsonUtils.toJson(data));
		return new ApiResult(jsonResult);
	}
}
