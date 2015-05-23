package blog.service;

import java.io.IOException;
import java.text.ParseException;

import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;

import blog.model.Article;
import blog.model.page.LucencePageBean;
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
	
	/**
	 * 使用lucence搜索
	 * @param search 搜索的内容(在标题以及摘要中找)
	 * @param pn 页码
	 * @param pageSize 每页大小
	 */
	public LucencePageBean<Article> search(String search, int pn, int pageSize) throws IOException, InvalidTokenOffsetsException, NumberFormatException, ParseException;

}
