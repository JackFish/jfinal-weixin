package com.jfinal.weixin.sdk.api;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.kit.HttpKit;
import com.jfinal.kit.StrKit;
import com.jfinal.weixin.sdk.utils.IOUtils;
import com.jfinal.weixin.sdk.utils.JsonUtils;

/**
 * 素材管理，待完善
 * @author l.cm
 * 文档：http://mp.weixin.qq.com/wiki/5/963fc70b80dc75483a271298a76a8d59.html
 */
public class MediaApi {
	private static final String DEFAULT_CHARSET = "UTF-8";
	private static final String DEFAULT_USER_AGENT = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36";
	
	/**
	 * 上传的临时多媒体文件有格式
	 */
	public static enum MediaType {
		IMAGE, VOICE, VIDEO, THUMB, NEWS;
		
		// 转化成小写形式
		public String get() {
			return this.name().toLowerCase();
		}
	}
	
	// 新增临时素材
	private static String upload_url = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=";
	
	/**
	 * 上传临时素材
	 * @param mediaType 上传的临时多媒体文件有格式
	 * @param file 需要上传的文件
	 * @return ApiResult 
	 */
	public static ApiResult uploadMedia(MediaType mediaType, File file) {
		String url = upload_url + AccessTokenApi.getAccessTokenStr() + "&type=" + mediaType.get();
		try {
			return uploadMedia(url, file, null);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static String get_url = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=";
	
	/**
	 * 获取临时素材
	 * @param media_id 素材Id
	 * @return MediaFile
	 */
	public static MediaFile getMedia(String media_id) {
		String url = get_url + AccessTokenApi.getAccessTokenStr() + "&media_id=" + media_id;
		try {
			return download(url);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static String add_news_url = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=";
	
	/**
	 * 新增永久图文素材
	 * @param mediaArticles 图文列表
	 * @return ApiResult
	 */
	public static ApiResult addNews(List<MediaArticles> mediaArticles) {
		String url = add_news_url + AccessTokenApi.getAccessTokenStr();
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("articles", mediaArticles);
		
		String jsonResult = HttpKit.post(url, JsonUtils.toJson(dataMap));
		return new ApiResult(jsonResult);
	}
	
	private static String uploadImgUrl = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=";
	
	/**
	 * 上传图文消息内的图片获取URL 
	 * 请注意，本接口所上传的图片不占用公众号的素材库中图片数量的5000个的限制。
	 * 图片仅支持jpg/png格式，大小必须在1MB以下。
	 * @param imgFile 图片文件
	 * @return ApiResult
	 */
	public static ApiResult uploadImg(File imgFile) {
		String url = uploadImgUrl + AccessTokenApi.getAccessTokenStr();
		try {
			return uploadMedia(url, imgFile, null);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static String addMaterialUrl = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=";
	
	/**
	 * 新增其他类型永久素材
	 * @return ApiResult
	 */
	public static ApiResult addMaterial(File file) {
		String url = addMaterialUrl + AccessTokenApi.getAccessTokenStr();
		
		try {
			return uploadMedia(url, file, null);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 新增视频永久素材
	 * 素材的格式大小等要求与公众平台官网一致。
	 * 具体是，图片大小不超过2M，支持bmp/png/jpeg/jpg/gif格式，语音大小不超过5M，长度不超过60秒，支持mp3/wma/wav/amr格式
	 * @return ApiResult
	 */
	public static ApiResult addMaterial(File file, String title, String introduction) {
		String url = addMaterialUrl + AccessTokenApi.getAccessTokenStr();
		
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("title", title);
		dataMap.put("introduction", introduction);
		
		try {
			return uploadMedia(url, file, JsonUtils.toJson(dataMap));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	// 获取永久素材
	private static String get_material_url = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=";
	
	/**
	 * 获取永久素材
	 * @param media_id 要获取的素材的media_id
	 * @param mediaType 素材分图文消息，视频素材和其他素材
	 * @return InputStream 流，考虑到这里可能返回json或file请自行使用IOUtils转换
	 */
	public static InputStream getMaterial(String media_id) {
		String url = get_material_url + AccessTokenApi.getAccessTokenStr();
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("media_id", media_id);
		
		try {
			return downloadMaterial(url, JsonUtils.toJson(dataMap));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
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
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("media_id", media_id);
		
		String jsonResult = HttpKit.post(url, JsonUtils.toJson(dataMap));
		return new ApiResult(jsonResult);
	}
	
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
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("media_id", media_id);
		dataMap.put("index", index);
		dataMap.put("articles", mediaArticles);
		
		String jsonResult = HttpKit.post(url, JsonUtils.toJson(dataMap));
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
		
		if(offset < 0) offset = 0;
		if(count > 20) count = 20;
		if(count < 1) count = 1;
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("type", mediaType.get());
		dataMap.put("offset", offset);
		dataMap.put("count", count);
		
		String jsonResult = HttpKit.post(url, JsonUtils.toJson(dataMap));
		return new ApiResult(jsonResult);
	}
	
	/**
	 * 上传临时素材，本段代码来自老版本（____′↘夏悸 / wechat），致敬！
	 * @param url 图片上传地址
	 * @param file 需要上传的文件
	 * @return ApiResult
	 * @throws IOException
	 */
	private static ApiResult uploadMedia(String url, File file, String params) throws IOException {
		URL urlGet = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) urlGet.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("connection", "Keep-Alive");
		conn.setRequestProperty("user-agent", DEFAULT_USER_AGENT);  
		conn.setRequestProperty("Charsert", "UTF-8");
		// 定义数据分隔线
		String BOUNDARY = "----WebKitFormBoundaryiDGnV9zdZA1eM1yL"; 
		conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
		
		OutputStream out = new DataOutputStream(conn.getOutputStream());
		// 定义最后数据分隔线
		StringBuilder mediaData = new StringBuilder();
		mediaData.append("--").append(BOUNDARY).append("\r\n");
		mediaData.append("Content-Disposition: form-data;name=\"media\";filename=\""+ file.getName() + "\"\r\n");
		mediaData.append("Content-Type:application/octet-stream\r\n\r\n");
		byte[] mediaDatas = mediaData.toString().getBytes();
		out.write(mediaDatas);
		DataInputStream fs = new DataInputStream(new FileInputStream(file));
		int bytes = 0;  
		byte[] bufferOut = new byte[1024];
		while ((bytes = fs.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		IOUtils.closeQuietly(fs);
		// 多个文件时，二个文件之间加入这个
		out.write("\r\n".getBytes());
		if (StrKit.notBlank(params)) {
			StringBuilder paramData = new StringBuilder();
			paramData.append("--").append(BOUNDARY).append("\r\n");
			paramData.append("Content-Disposition: form-data;name=\"description\";");
			byte[] paramDatas = paramData.toString().getBytes();
			out.write(paramDatas);
			out.write(params.getBytes(DEFAULT_CHARSET));
		}
		byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
		out.write(end_data);
		out.flush();
		IOUtils.closeQuietly(out);
		
		// 定义BufferedReader输入流来读取URL的响应  
		InputStream in = conn.getInputStream();
		BufferedReader read = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
		String valueString = null;
		StringBuffer bufferRes = null;
		bufferRes = new StringBuffer();
		while ((valueString = read.readLine()) != null){
			bufferRes.append(valueString);
		}
		IOUtils.closeQuietly(in);
		// 关闭连接
		if (conn != null) {
			conn.disconnect();
		}
		return new ApiResult(bufferRes.toString());
	}
	
	/**
	 * 获取永久素材
	 * @param url 素材地址
	 * @return params post参数
	 * @return InputStream 流，考虑到这里可能返回json或file
	 * @throws IOException
	 */
	private static InputStream downloadMaterial(String url, String params) throws IOException {
		URL _url = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) _url.openConnection();
		// 连接超时
		conn.setConnectTimeout(25000);
		// 读取超时 --服务器响应比较慢，增大时间
		conn.setReadTimeout(25000);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "Keep-Alive");
		conn.setRequestProperty("User-Agent", DEFAULT_USER_AGENT);
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.connect();
		if (StrKit.notBlank(params)) {
			OutputStream out = conn.getOutputStream();
			out.write(params.getBytes(DEFAULT_CHARSET));
			out.flush();
			IOUtils.closeQuietly(out);
		}
		InputStream input = conn.getInputStream();
//		// 关闭连接
//		if (conn != null) {
//			conn.disconnect();
//		}
		return input;
	}
	
	/**
	 * 下载素材，本段代码来自老版本（____′↘夏悸 / wechat），致敬！
	 * @param url 素材地址
	 * @return MediaFile
	 * @throws IOException
	 */
	private static MediaFile download(String url) throws IOException {
		MediaFile mediaFile = new MediaFile();
		URL _url = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) _url.openConnection();
		// 连接超时
		conn.setConnectTimeout(25000);
		// 读取超时 --服务器响应比较慢，增大时间
		conn.setReadTimeout(25000);
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		conn.setRequestProperty("User-Agent", DEFAULT_USER_AGENT);
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.connect();
		
		if(conn.getContentType().equalsIgnoreCase("text/plain")){
			// 定义BufferedReader输入流来读取URL的响应  
			InputStream in = conn.getInputStream();
			BufferedReader read = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
			String valueString = null;
			StringBuffer bufferRes = new StringBuffer();
			while ((valueString = read.readLine()) != null){
				bufferRes.append(valueString);
			}
			read.close();
			IOUtils.closeQuietly(in);
			mediaFile.setError(bufferRes.toString());
		}else{
			BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());  
			String ds = conn.getHeaderField("Content-disposition");
			String fullName = ds.substring(ds.indexOf("filename=\"") + 10, ds.length() - 1);
			String relName = fullName.substring(0, fullName.lastIndexOf("."));
			String suffix = fullName.substring(relName.length()+1);
			
			mediaFile.setFullName(fullName);
			mediaFile.setFileName(relName);
			mediaFile.setSuffix(suffix);
			mediaFile.setContentLength(conn.getHeaderField("Content-Length"));
			mediaFile.setContentType(conn.getHeaderField("Content-Type"));
			mediaFile.setFileStream(bis);
			
			IOUtils.closeQuietly(bis);
		}
		// 关闭连接
		if (conn != null) {
			conn.disconnect();
		}
		return mediaFile;
	}
	
}
