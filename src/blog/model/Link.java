package blog.model;

import java.io.Serializable;

/**
 * 链接
 * @author skywalker
 *
 */
public class Link implements Serializable {

	private static final long serialVersionUID = 37007122475579489L;
	
	private int id;
	private String name;
	private String url;
	
	public Link(String name, String url) {
		this.name = name;
		this.url = url;
	}
	
	public Link() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
