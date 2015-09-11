package com.yibu.kuaibu.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	// MD5��Q
	public static String Md5(String str) {
		String e_md5 = null;
		try{
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(str.getBytes("UTF-8"));
			e_md5 = parseByte2HexStr(md5.digest());
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return e_md5;
	}

	/**
	 * 将二进制转换成16进制
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}
	
}
