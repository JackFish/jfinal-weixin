/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.kit;

import java.security.MessageDigest;

public class EncryptionKit {
	
	public static String md5Encrypt(String srcStr){
		return encrypt("MD5", srcStr);
	}
	
	public static String sha1Encrypt(String srcStr){
		return encrypt("SHA-1", srcStr);
	}
	
	public static String sha256Encrypt(String srcStr){
		return encrypt("SHA-256", srcStr);
	}
	
	public static String sha384Encrypt(String srcStr){
		return encrypt("SHA-384", srcStr);
	}
	
	public static String sha512Encrypt(String srcStr){
		return encrypt("SHA-512", srcStr);
	}
	
	public static String encrypt(String algorithm, String srcStr) {
		try {
			StringBuilder result = new StringBuilder();
			MessageDigest md = MessageDigest.getInstance(algorithm);
			byte[] bytes = md.digest(srcStr.getBytes("utf-8"));
			for (byte b :bytes) {
				String hex = Integer.toHexString(b&0xFF);
				if (hex.length() == 1)
					result.append("0");
				result.append(hex);
			}
			return result.toString();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}





