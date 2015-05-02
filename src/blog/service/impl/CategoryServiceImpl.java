package blog.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import blog.dao.base.BaseDao;
import blog.model.Category;
import blog.service.CategoryService;
import blog.service.base.BaseServiceImpl;

@Service("categoryService")
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {

	@Override
	@Resource(name = "categoryDao")
	protected void setBaseDao(BaseDao<Category> baseDao) {
		super.setBaseDao(baseDao);
	}
	
}
