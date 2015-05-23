package blog.util;

import java.util.Map;

/**
 * 获取属性文件中的配置值
 * @author skywalker
 *
 */
public class PropertyUtil {

	/**
	 * 需要PropertyListener赋值
	 */
	public static Map<String, String> PROPERTIES = null;
 	
	/**
	 * 获取属性配置
	 */
	public static String getProperty(String key) {
		return PROPERTIES.get(key);
	}
	
}
