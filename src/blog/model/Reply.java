package blog.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 回复
 * @author skywalker
 *
 */
public class Reply implements Serializable {
	
	private static final long serialVersionUID = -7693156834553783739L;
	
	private int id;
	private String headpath;
	private String nickname;
	private String content;
	private String email;
	private String homepage;
	private Date replytime = new Date();
	private Article article;
	private Reply parent;
	/**所属的留言**/
	private Message message;
	/**子回复**/
	private Set<Reply> children = new HashSet<Reply>();
	
	public Reply(String nickname, String content, String email, String homepage) {
		this.nickname = nickname;
		this.content = content;
		this.email = email;
		this.homepage = homepage;
	}
	
	public Reply(int id) {
		this.id = id;
	}
	
	public Reply() {}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHeadPath() {
		return headpath;
	}
	public void setHeadPath(String headPath) {
		this.headpath = headPath;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getReplytime() {
		return replytime;
	}
	public void setReplytime(Date replytime) {
		this.replytime = replytime;
	}

	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public String getHeadpath() {
		return headpath;
	}
	public void setHeadpath(String headpath) {
		this.headpath = headpath;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public Reply getParent() {
		return parent;
	}
	public void setParent(Reply parent) {
		this.parent = parent;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public Set<Reply> getChildren() {
		return children;
	}
	public void setChildren(Set<Reply> children) {
		this.children = children;
	}
}
