package blog.model;

import java.io.Serializable;

/**
 * 用户
 * @author skywalker
 *
 */
public class User implements Serializable {

	private static final long serialVersionUID = 2198556388997159783L;
	
	private int id;
	private String username;
	private String password;
	//关于
	private String aboutUs;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAboutUs() {
		return aboutUs;
	}
	public void setAboutUs(String aboutUs) {
		this.aboutUs = aboutUs;
	}
	
}
