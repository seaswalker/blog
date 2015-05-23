package blog.test;

import java.text.ParseException;
import java.util.Date;

import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.DateTools.Resolution;

/**
 * 测试日期转换
 * @author skywalker
 *
 */
public class DateTest {

	public static void main(String[] args) throws ParseException {
		//String str = "2015-05-23";
		//Date date = DateTools.stringToDate(str);
		//Date date = new SimpleDateFormat("yyyy-MM-dd").parse(str);
		Date date = new Date();
		System.out.println(DateTools.stringToDate(DateTools.dateToString(date, Resolution.DAY)));
	}
	
}
