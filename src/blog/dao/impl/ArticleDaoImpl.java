package blog.dao.impl;

import org.springframework.stereotype.Repository;

import blog.dao.ArticleDao;
import blog.dao.base.DaoSupport;
import blog.model.Article;

@Repository("articleDao")
public class ArticleDaoImpl extends DaoSupport<Article> implements ArticleDao {

}
