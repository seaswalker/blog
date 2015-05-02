package blog.service;

import java.util.List;

import blog.model.Reply;
import blog.service.base.BaseService;

public interface ReplyService extends BaseService<Reply> {

	/**
	 * 决定加载博文的评论还是留言板的评论
	 * true加载博文的评论
	 * @param id 博文或者是留言的id
	 */
	public List<Reply> findAll(boolean isArticle, Integer id);
	
}
