package blog.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 类别
 * @author skywalker
 *
 */
public class Category implements Serializable {

	private static final long serialVersionUID = 6517566559755024176L;
	
	private int id;
	private String name;
	private Set<Article> articles = new HashSet<Article>();
	
	public Category(int id) {
		this.id = id;
	}
	
	public Category() {}
	
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
	public Set<Article> getArticles() {
		return articles;
	}
	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}
	
}
