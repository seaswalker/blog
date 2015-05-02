package blog.dao.impl;

import org.springframework.stereotype.Repository;

import blog.dao.ReplyDao;
import blog.dao.base.DaoSupport;
import blog.model.Reply;

@Repository("replyDao")
public class ReplyDaoImpl extends DaoSupport<Reply> implements ReplyDao {

}
