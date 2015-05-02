package blog.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import blog.dao.base.BaseDao;
import blog.model.Article;
import blog.model.Reply;
import blog.service.ArticleServcie;
import blog.service.base.BaseServiceImpl;

/**
 * 大家好
 */
@Service("articleService")
public class ArticleServcieImpl extends BaseServiceImpl<Article> implements ArticleServcie {
	
	private BaseDao<Article> baseDao;
	
	@Resource(name = "articleDao")
	@Override
	protected void setBaseDao(BaseDao<Article> baseDao) {
		this.baseDao = baseDao;
		super.setBaseDao(baseDao);
	}
	
	@Override
	public void delete(Serializable id) {
		//删除该博文关联的标签(如果没有其它博文拥有此标签)
		String sql = "delete from tag where id in(select tagid from article_tag where articleid = " + id + ") "
				+ "and (select count(articleid) from  article_tag where tagid = tag.id) = 1";
		baseDao.executeSql(sql);
		//删除博文
		//首先得到全部回复
		Article article = baseDao.findById(id);
		//解除和父回复的关联
		for(Reply reply : article.getReplies()) {
			reply.setParent(null);
		}
		baseDao.delete(article);
	}
	
	@Override
	public void addReplyCount(int id) {
		String sql = "update article set replycount = replycount + 1 where id = " + id;
		executeSql(sql);
	}
	
	@Override
	public void addClickCount(int id) {
		String sql = "update article set clickcount = clickcount + 1 where id = " + id;
		executeSql(sql);
	}
	
}
