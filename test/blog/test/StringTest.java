package blog.test;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import blog.util.HTMLUtil;

/**
 * 字符串相关测试
 * @author skywalker
 *
 */
public class StringTest {

	@Test
	public void json() {
		long begin = System.currentTimeMillis();
		List<String> dates = new ArrayList<String>();
		dates.add("201305");
		dates.add("201306");
		dates.add("201307");
		dates.add("201308");
		dates.add("201309");
		dates.add("201310");
		//转为json数组
		//JSONArray array = JSONArray.fromObject(dates);
		//System.out.println(array.toString());
		
		StringBuilder sb = new StringBuilder("[");
		for(String date : dates) {
			sb.append("{'year':'").append(date.substring(0, 4))
				.append("','month':'").append(date.substring(4, 6)).append("'},");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		System.out.println(sb.toString());
		long end = System.currentTimeMillis();
		System.out.println("共消耗" + (end - begin) + "毫秒");
	}
	
	@Test
	public void date() {
		//System.out.println(new Date().toString());
		System.out.println(DateFormat.getDateTimeInstance().format(new Date()));
	}
	
	@Test
	public void newLine() {
		String str = "test\ntest";
		System.out.println(str.replaceAll("\n", ""));
	}
	
	/**
	 * 测试html转纯文本
	 */
	@Test
	public void HTML() {
		String str = "<p>&nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;<img src='http://localhost:8080/Blog/umeditor/dialogs/emotion/images/jx2/j_0024.gif' _src='http://localhost:8080/Blog/umeditor/dialogs/emotion/images/jx2/j_0024.gif'/> &nbsp; &nbsp; &nbsp;呵呵哒</p>";
		System.out.println(HTMLUtil.HtmltoText(str));
	}
	
}
