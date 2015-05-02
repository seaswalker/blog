package blog.dao.impl;

import org.springframework.stereotype.Repository;

import blog.dao.MessageDao;
import blog.dao.base.DaoSupport;
import blog.model.Message;

@Repository("messageDao")
public class MessageDaoImpl extends DaoSupport<Message> implements MessageDao {

}
