package blog.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import blog.dao.base.BaseDao;
import blog.model.Message;
import blog.service.MessageService;
import blog.service.base.BaseServiceImpl;

@Service("messageService")
public class MessageServiceImpl extends BaseServiceImpl<Message> implements MessageService {

	@Resource(name = "messageDao")
	@Override
	protected void setBaseDao(BaseDao<Message> baseDao) {
		super.setBaseDao(baseDao);
	}
	
}
