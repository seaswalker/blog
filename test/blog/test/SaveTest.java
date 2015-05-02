package blog.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import blog.model.Article;
import blog.model.Category;
import blog.service.ArticleServcie;
import blog.util.HTMLUtil;
import blog.util.StringUtil;

/**
 * 保存测试
 * @author skywalker
 *
 */
public class SaveTest {
	
	private static ClassPathXmlApplicationContext context;

	@BeforeClass
	public static void setUp() {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	@Test
	public void testSaveBlog() {
		ArticleServcie articleServcie  = (ArticleServcie) context.getBean("articleService");
		Article article = new Article();
		//转为纯为本
		String text = HTMLUtil.HtmltoText("<p>测试内容</p>");
		article.setDigest(StringUtil.subString(text, 32));
		article.setCategory(new Category(1));
		article.setContent("<p>测试内容</p>");
		article.setTitle("测试");
		//DataUtil.convertTags(article, "心情 测试");
		articleServcie.saveOrUpdate(article);
		System.out.println("保存成功");
	}
	
	@Test
	public void deleteBlog() {
		ArticleServcie articleServcie  = (ArticleServcie) context.getBean("articleService");
		articleServcie.delete(4);
		System.out.println("删除成功");
	}
	
}
