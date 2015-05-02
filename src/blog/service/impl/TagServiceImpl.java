package blog.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import blog.dao.base.BaseDao;
import blog.model.Tag;
import blog.service.TagService;
import blog.service.base.BaseServiceImpl;
import blog.util.StringUtil;

@Service("tagService")
public class TagServiceImpl extends BaseServiceImpl<Tag> implements TagService {

	@Override
	@Resource(name = "tagDao")
	protected void setBaseDao(BaseDao<Tag> baseDao) {
		super.setBaseDao(baseDao);
	}
	
	@Override
	public Tag getByTagName(String tagName) {
		if(StringUtil.isEmpty(tagName)) {
			return null;
		}
		return (Tag) query("from Tag where name = ?", new Object[]{tagName});
	}
	
}
