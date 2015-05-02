package blog.service;

import blog.model.Article;
import blog.service.base.BaseService;

/**
 * 博文业务逻辑
 * @author skywalker
 *
 */
public interface ArticleServcie extends BaseService<Article> {
	
	/**
	 * 评论数量加一
	 */
	public void addReplyCount(int id);
	
	/**
	 * 点击数量加一
	 */
	public void addClickCount(int id);

}
