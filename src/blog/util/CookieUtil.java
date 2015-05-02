package blog.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 操作cookies
 * @author skywalker
 *
 */
public class CookieUtil {

	/**
	 * 保存cookies
	 */
	public static void saveCookie(String name, String value, HttpServletResponse response) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(60 * 60 * 24 * 7);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	public static String getCookieValue(String name, HttpServletRequest request) {
		Cookie []cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals(name)) {
				return cookie.getValue();
			}
		}
		return "";
	}
	
	public static void removeCookie(String name, HttpServletResponse response) {
		Cookie cookie = new Cookie(name, null);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
	
}
