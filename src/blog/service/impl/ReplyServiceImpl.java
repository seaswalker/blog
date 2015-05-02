package blog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import blog.dao.base.BaseDao;
import blog.model.Reply;
import blog.service.ReplyService;
import blog.service.base.BaseServiceImpl;

@Service("replyService")
public class ReplyServiceImpl extends BaseServiceImpl<Reply> implements ReplyService {
	
	@Override
	@Resource(name = "replyDao")
	protected void setBaseDao(BaseDao<Reply> baseDao) {
		super.setBaseDao(baseDao);
	}
	
	@Override
	public List<Reply> findAll(boolean isArticle, Integer id) {
		String hql = "from Reply";
		if(isArticle) {
			hql += " where articleid = " + id;
		}else {
			hql += " where messageid = " + id;
		}
		hql += " order by replytime desc";
		return batchQuery(hql, new Object[] {});
	}
	
}
