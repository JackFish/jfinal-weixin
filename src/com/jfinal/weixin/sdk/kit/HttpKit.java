package com.jfinal.weixin.sdk.kit;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

public class HttpKit {
	
	private HttpKit() {}
	
	public static String readData(HttpServletRequest request) {
		BufferedReader br = null;
		try {
			StringBuilder result = new StringBuilder();
			br = request.getReader();
			for (String line=null; (line=br.readLine())!=null;) {
				result.append(line).append("\n");
			}
			
			return result.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		finally {
			if (br != null)
				try {br.close();} catch (IOException e) {e.printStackTrace();}
		}
	}
	
	public static String httpPost() {
		throw new RuntimeException("尚未开发完成");
	}
	
	public static String httpGet() {
		throw new RuntimeException("尚未开发完成");
	}
}






