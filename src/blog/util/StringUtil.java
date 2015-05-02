package blog.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtil {
	
	public static boolean isEmpty(String str) {
		if(str == null || str.trim().equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * 执行md5加密操作
	 */
	public static String md5(String str) {
		StringBuilder result = new StringBuilder("");
		if(!isEmpty(str)) {
			char []chars = {'0', '1', '2','3','4','5','6','7',
					'8','9','a','b','c','d','e', 'f'};
			byte []origin = str.getBytes();
			MessageDigest md = null;;
			try {
				md = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			byte []after = md.digest(origin);
			for(byte b : after) {
				result.append(chars[(b >> 4) & 0xF]);
				result.append(chars[b & 0xF]);
			}
		}
		return result.toString();
	}
	
	/**
	 * 截取字符串
	 */
	public static String subString(String str, int limit) {
		if(isEmpty(str) || limit <= 0) {
			return "";
		}
		if(str.length() <= limit) {
			return str;
		}
		return str.substring(0, limit);
	}
	
}
