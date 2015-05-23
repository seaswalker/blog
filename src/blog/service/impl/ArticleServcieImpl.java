package blog.service.impl;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.springframework.stereotype.Service;

import blog.dao.base.BaseDao;
import blog.model.Article;
import blog.model.Reply;
import blog.model.page.LucencePageBean;
import blog.service.ArticleServcie;
import blog.service.base.BaseServiceImpl;
import blog.util.LucenceUtil;
import blog.util.StringUtil;

/**
 * 大家好
 */
@Service("articleService")
public class ArticleServcieImpl extends BaseServiceImpl<Article> implements ArticleServcie {
	
	private BaseDao<Article> baseDao;
	
	@Resource(name = "articleDao")
	@Override
	protected void setBaseDao(BaseDao<Article> baseDao) {
		this.baseDao = baseDao;
		super.setBaseDao(baseDao);
	}
	
	@Override
	public void delete(Serializable id) {
		//删除该博文关联的标签(如果没有其它博文拥有此标签)
		String sql = "delete from tag where id in(select tagid from article_tag where articleid = " + id + ") "
				+ "and (select count(articleid) from  article_tag where tagid = tag.id) = 1";
		baseDao.executeSql(sql);
		//删除博文
		//首先得到全部回复
		Article article = baseDao.findById(id);
		//解除和父回复的关联
		for(Reply reply : article.getReplies()) {
			reply.setParent(null);
		}
		baseDao.delete(article);
	}
	
	@Override
	public void addReplyCount(int id) {
		String sql = "update article set replycount = replycount + 1 where id = " + id;
		executeSql(sql);
	}
	
	@Override
	public void addClickCount(int id) {
		String sql = "update article set clickcount = clickcount + 1 where id = " + id;
		executeSql(sql);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public LucencePageBean<Article> search(String search, int pn, int pageSize) throws IOException, InvalidTokenOffsetsException, NumberFormatException, ParseException {
		if(!StringUtil.isEmpty(search)) {
			//统计耗时
			long begin = System.currentTimeMillis();
			IndexSearcher indexSearcher = LucenceUtil.getIndexSearcher();
			//布尔查询
			BooleanQuery query = new BooleanQuery();
			List<Article> articles = null;
			//检查缓存
			if((articles = LucenceUtil.checkCache(search)) == null) {
				Query searchQuery = new TermQuery(new Term("title", search));
				query.add(searchQuery, Occur.SHOULD);
				searchQuery = new TermQuery(new Term("digest", search));
				query.add(searchQuery, Occur.SHOULD);
				TopDocs topDocs = indexSearcher.search(query, 100);
				ScoreDoc[] scoreDocs = topDocs.scoreDocs;
				//文档序号
				int sn = 0;
				Document doc = null;
				
				//高亮显示
				Formatter formatter = new SimpleHTMLFormatter("<span style='color:red;'>", "</span>");
				Scorer scorer = new QueryScorer(searchQuery);
				Highlighter highlighter = new Highlighter(formatter, scorer);
				//设置高亮词数限制
				Fragmenter fragmenter = new SimpleFragmenter(32);
				highlighter.setTextFragmenter(fragmenter);
				
				articles = new ArrayList<Article>();
				Analyzer analyzer = LucenceUtil.getAnalyzer();
				for(ScoreDoc socDoc : scoreDocs) {
					sn = socDoc.doc;
					doc = indexSearcher.doc(sn);
					String title = doc.get("title");
					String hcTitle = highlighter.getBestFragment(analyzer, "title", title);
					String digest = doc.get("digest");
					String hcDigest = highlighter.getBestFragment(analyzer, "digest", digest);
					articles.add(new Article(Integer.parseInt(doc.get("id")), ((hcTitle == null) ? title : hcTitle), 
							Integer.parseInt(doc.get("replyCount")), Integer.parseInt(doc.get("clickCount")),
							DateTools.stringToDate(doc.get("date")), ((hcDigest == null) ? digest : hcDigest)));
					
				}
				LucenceUtil.putIntoCache(search, articles);
			}
			long end = System.currentTimeMillis();
			long cost = end - begin;
			int fromIndex = (pn - 1) * pageSize;
			int size = articles.size();
			int toIndex = pn * pageSize > size ? size : pn * pageSize;
			return new LucencePageBean<Article>(articles.subList(fromIndex, toIndex), pageSize, pn, 
					size, cost);
		}
		return LucencePageBean.empty();
	}
	
}
