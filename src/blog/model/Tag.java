package blog.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 标签
 * @author skywalker
 *
 */
public class Tag implements Serializable {

	private static final long serialVersionUID = -1911759879047031763L;
	
	private int id;
	private String name;
	/**和文章多对多**/
	private Set<Article> articles = new HashSet<Article>();
	
	public Tag(String name) {
		this.name = name;
	}
	
	public Tag() {}

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
