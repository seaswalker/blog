package blog.service;

import blog.model.Tag;
import blog.service.base.BaseService;

public interface TagService extends BaseService<Tag> {
	
	/**
	 * 根据tag名称获取标签对象
	 */
	public Tag getByTagName(String tagName);

}
