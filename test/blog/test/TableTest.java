package blog.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import blog.model.Article;
import blog.model.User;
import blog.service.ArticleServcie;
import blog.service.UserService;
import blog.util.StringUtil;

public class TableTest {
	
	private static ClassPathXmlApplicationContext context;

	@BeforeClass
	public static void setUp() {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	@Test
	public void saveUser() {
		User user = new User();
		user.setUsername("skywalker");
		user.setPassword(StringUtil.md5("1234"));
		UserService userService = (UserService) context.getBean("userService");
		userService.saveOrUpdate(user);
		System.out.println("保存成功");
	}
	
	@Test
	public void query() {
		ArticleServcie articleService = (ArticleServcie) context.getBean("articleService");
		String hql = "from Article where id = ? limit 0,1";
		Article article = (Article) articleService.query(hql, new Object[]{4});
		System.out.println(article);
	}
	
}
