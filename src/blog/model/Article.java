package blog.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 博客
 * @author skywalker
 *
 */
public class Article implements Serializable {

	private static final long serialVersionUID = -2701231628790872290L;
	
	private int id;
	private String title;
	private String content;
	private int replyCount;
	private int clickCount;
	private Date createTime = new Date();
	private String digest;
	/*和类别多对一*/
	private Category category;
	/**和标签多对多**/
	private Set<Tag> tags = new HashSet<Tag>();
	//用于修改时显示的标签
	private String tagsStr;
	//回复
	private Set<Reply> replies;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	public int getClickCount() {
		return clickCount;
	}
	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getDigest() {
		return digest;
	}
	public void setDigest(String digest) {
		this.digest = digest;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Set<Tag> getTags() {
		return tags;
	}
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	public String getTagsStr() {
		return tagsStr;
	}
	public void setTagsStr(String tagsStr) {
		this.tagsStr = tagsStr;
	}
	public Set<Reply> getReplies() {
		return replies;
	}
	public void setReplies(Set<Reply> replies) {
		this.replies = replies;
	}
	public void tagsStr() {
		StringBuilder sb = new StringBuilder();
		for(Tag tag : tags) {
			sb.append(tag.getName()).append(" ");
		}
		this.tagsStr = sb.deleteCharAt(sb.length() - 1).toString();
	}

}
