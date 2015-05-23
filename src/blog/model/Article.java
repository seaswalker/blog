package blog.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.DateTools.Resolution;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;

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
	
	public Article(int id, String title, int replyCount, int clickCount,
			Date createTime, String digest) {
		super();
		this.id = id;
		this.title = title;
		this.replyCount = replyCount;
		this.clickCount = clickCount;
		this.createTime = createTime;
		this.digest = digest;
	}
	
	public Article() {}

	/**
	 * 拿到此篇博客的doc
	 */
	public Document getDocument() {
		Document doc = new Document();
		Field idField = new StringField("id", String.valueOf(this.id), Store.YES);
		Field titleField = new TextField("title", this.title, Store.YES);
		titleField.setBoost(2f);
		Field digestField = new TextField("digest", this.digest, Store.YES);
		digestField.setBoost(1f);
		//日期字段
		Field dateField = new StringField("date", DateTools.dateToString(createTime, Resolution.DAY), Store.YES);
		//点击数和回复数
		Field clickCountField = new IntField("clickCount", this.clickCount, Store.YES);
		Field replyCountField = new IntField("replyCount", this.replyCount, Store.YES);
		doc.add(idField);
		doc.add(titleField);
		doc.add(digestField);
		doc.add(dateField);
		doc.add(replyCountField);
		doc.add(clickCountField);
		return doc;
	}
	
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
