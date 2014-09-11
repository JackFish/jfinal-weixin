package com.jfinal.weixin.sdk.kit;

import java.security.MessageDigest;

public class EncryptionKit {
	
	public static final String[] algorithms={"MD5","SHA-1","SHA-256","SHA-384","SHA-512"};
	
	public static String md5Encrypt(String srcStr){
		String result = "";
		try {
			result = encrypt("MD5",srcStr);
		} catch (Exception e) {
			new RuntimeException(e);
		}
		return result;
	}
	
	public static String sha1Encrypt(String srcStr){
		String result = "";
		try {
			result = encrypt("SHA-1",srcStr);
		} catch (Exception e) {
			new RuntimeException(e);
		}
		return result;
	}
	
	public static String sha256Encrypt(String srcStr){
		String result = "";
		try {
			result = encrypt("SHA-256",srcStr);
		} catch (Exception e) {
			new RuntimeException(e);
		}
		return result;
	}
	
	public static String sha384Encrypt(String srcStr){
		String result = "";
		try {
			result = encrypt("SHA-384",srcStr);
		} catch (Exception e) {
			new RuntimeException(e);
		}
		return result;
	}
	
	public static String sha512Encrypt(String srcStr){
		String result = "";
		try {
			result = encrypt("SHA-512",srcStr);
		} catch (Exception e) {
			new RuntimeException(e);
		}
		return result;
	}
	
	public static String encrypt(String algorithms,String srcStr) throws Exception {
		String result = "";
		MessageDigest md = MessageDigest.getInstance(algorithms);
		byte[] bytes = md.digest(srcStr.getBytes("utf-8"));
		for(byte b:bytes){
			String hex = Integer.toHexString(b&0xFF).toUpperCase();
			result += ((hex.length()==1)?"0":"")+hex;
		}
		return result;
	}
	
	public static void main(String[] args) throws Exception{
		System.out.println(EncryptionKit.md5Encrypt("qunar"));
//		Random rd = new Random(System.nanoTime()/new Random().nextInt());
//		String id = Encrypt.encrypt(Encrypt.algorithms[rd.nextInt()%5],""+rd.nextLong()+Runtime.getRuntime().freeMemory());
//		System.out.println(Encrypt.md5Encrypt("_input_charset=utf-8&partner=2088701290402889&service=sign_protocol_with_partner1dartv2j7o57zzjwozewwp217pid379o"));
	}
}





