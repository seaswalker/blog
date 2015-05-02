package blog.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import blog.dao.base.BaseDao;
import blog.model.Link;
import blog.service.LinkService;
import blog.service.base.BaseServiceImpl;

@Service("linkService")
public class LinkServiceImpl extends BaseServiceImpl<Link> implements LinkService {

	@Resource(name = "linkDao")
	@Override
	protected void setBaseDao(BaseDao<Link> baseDao) {
		super.setBaseDao(baseDao);
	}
	
}
