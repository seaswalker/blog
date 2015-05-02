package blog.service;

import blog.model.User;
import blog.service.base.BaseService;

public interface UserService extends BaseService<User> {

	/**
	 * 登录
	 */
	public User login(String username, String password);
	
	/**
	 * 获取关于信息
	 */
	public String getAbout();
	
}
