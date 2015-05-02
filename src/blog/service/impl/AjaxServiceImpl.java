package blog.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import blog.dao.base.BaseDao;
import blog.service.AjaxService;
import blog.service.base.BaseServiceImpl;

@Service("ajaxService")
public class AjaxServiceImpl extends BaseServiceImpl<String> implements AjaxService {
	
	@Resource(name = "ajaxDao")
	@Override
	protected void setBaseDao(BaseDao<String> baseDao) {
		super.setBaseDao(baseDao);
	}

}
