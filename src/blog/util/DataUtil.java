package blog.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import blog.model.Article;
import blog.model.Tag;
import blog.service.TagService;

public class DataUtil {

	/**
	 * 判断数组有效性
	 */
	public static boolean isValid(Object[] array) {
		if(array != null && array.length > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断集合有效性
	 */
	public static boolean isValid(@SuppressWarnings("rawtypes") Collection collection) {
		if(collection != null && collection.size() > 0) {
			return true;
		}
		return false;
	}
	
	public static boolean isValid(@SuppressWarnings("rawtypes") Map map) {
		if(map != null && map.size() > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断Integer类型有效性(非空大于零)
	 */
	public static boolean isValid(Integer i) {
		if(i != null && i > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 标签放到article
	 */
	public static void convertTags(Article article, String tagStr, TagService tagService) {
		if(!StringUtil.isEmpty(tagStr)) {
			Set<Tag> tags = article.getTags();
			Tag newTag = null;
			String []values = tagStr.split(" ");
			for(String tag : values) {
				newTag = tagService.getByTagName(tag);
				if(newTag == null) {
					newTag = new Tag(tag);
					tagService.saveOrUpdate(newTag);
				}
				tags.add(newTag);
			}
			//设置回显字符串
			article.tagsStr();
		}
	}
	
	/**
	 * 从文件读取
	 * @param path 文件路径
	 */
	public static String readFormFile(String path) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path)), "UTF-8"));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while((line = br.readLine()) != null) {
			sb.append(line).append("\r\n");
		}
		br.close();
		return sb.toString();
	}
	
}
