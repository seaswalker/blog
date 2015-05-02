package blog.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import blog.dao.base.BaseDao;
import blog.model.User;
import blog.service.UserService;
import blog.service.base.BaseServiceImpl;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Override
	@Resource(name = "userDao")
	protected void setBaseDao(BaseDao<User> baseDao) {
		super.setBaseDao(baseDao);
	}
	
	@Override
	public User login(String username, String password) {
		String hql = "from User where username = ? and password = ?";
		return (User) query(hql, username, password);
	}
	
	@Override
	public String getAbout() {
		String sql = "select aboutus from user where username = 'skywalker'";
		return (String) nativeQuery(sql, new Object[] {});
	}
	
}
