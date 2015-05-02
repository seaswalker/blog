package blog.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 留言
 * @author skywalker
 *
 */
public class Message implements Serializable {

	private static final long serialVersionUID = 8329300711841790765L;
	
	private int id;
	private String content;
	//回复
	private Set<Reply> replies = new HashSet<Reply>();
	
	public Message(int id, String content) {
		this.id = id;
		this.content = content;
	}
	
	public Message(int id) {
		this.id = id;
	}

	public Message() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Set<Reply> getReplies() {
		return replies;
	}
	public void setReplies(Set<Reply> replies) {
		this.replies = replies;
	}
	
}
