package blog.dao.impl;

import org.springframework.stereotype.Repository;

import blog.dao.CategoryDao;
import blog.dao.base.DaoSupport;
import blog.model.Category;

@Repository("categoryDao")
public class CategoryDaoImpl extends DaoSupport<Category> implements CategoryDao {

}
