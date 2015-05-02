package blog.dao.impl;

import org.springframework.stereotype.Repository;

import blog.dao.UserDao;
import blog.dao.base.DaoSupport;
import blog.model.User;

@Repository("userDao")
public class UserDaoImpl extends DaoSupport<User> implements UserDao {

}
