package blog.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;
import org.apache.lucene.util.Version;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import blog.model.Article;
import blog.service.ArticleServcie;

/**
 * Lucence工具类
 * @author skywalker
 *
 */
public class LucenceUtil {

	//private static String lucenceIndex = PropertyUtil.getProperty("lucence.index.path");
	private static String lucenceIndex = "D:\\java\\eclipse\\Blog\\indexes";
	private volatile static Analyzer analyzer;
	private volatile static IndexSearcher indexSearcher;
	/**
	 * 搜索缓存
	 * value为封装好的博文列表，可以省去组装的时间
	 */
	private static final ConcurrentHashMap<String, List<Article>> LUCENCECACHE = new ConcurrentHashMap<String, List<Article>>();
	
	/**
	 * 检查缓存
	 */
	public static List<Article> checkCache(String key) {
		return LUCENCECACHE.get(key);
	}
	
	/**
	 * 加入缓存
	 */
	public static void putIntoCache(String key, List<Article> value) {
		LUCENCECACHE.put(key, value);
	}
	
	/**
	 * 清空缓存
	 */
	public static void clearCache() {
		LUCENCECACHE.clear();
	}
	
	/**
	 * 单例获取分词器
	 */
	public static Analyzer getAnalyzer() {
		if(analyzer == null) {
			synchronized (LucenceUtil.class) {
				if(analyzer == null) {
					analyzer = new SmartChineseAnalyzer();
				}
			}
		}
		return analyzer;
	}
	
	/**
	 * 获得IndexSearcher 单例
	 */
	public static IndexSearcher getIndexSearcher() throws IOException {
		if(indexSearcher == null) {
			synchronized (LucenceUtil.class) {
				if(indexSearcher == null) {
					generateIndexSearcher();
				}
			}
		}
		return indexSearcher;
	}
	
	/**
	 * 生成IndexSearcher
	 */
	private static void generateIndexSearcher() throws IOException {
		IndexReader reader = DirectoryReader.open(MMapDirectory.open(new File(lucenceIndex)));
		indexSearcher = new IndexSearcher(reader);
	}
	
	/**
	 * 为一篇博文建立索引
	 */
	public static void addIndex(Article article) throws IOException {
		IndexWriter indexWriter = getIndexWriter();
		indexWriter.addDocument(article.getDocument());
		closeIndexWriter(indexWriter);
		//重新生成搜索器
		generateIndexSearcher();
	}
	
	/**
	 * 为此篇博文重建索引
	 */
	public static void updateIndex(Article article) throws IOException {
		IndexWriter indexWriter = getIndexWriter();
		indexWriter.updateDocument(new Term("id", String.valueOf(article.getId())), article.getDocument());
		closeIndexWriter(indexWriter);
		generateIndexSearcher();
	}
	
	/**
	 * 删除博文对应的索引
	 * @param id 博文id
	 */
	public static void deleteIndex(int id) throws IOException {
		IndexWriter indexWriter = getIndexWriter();
		indexWriter.deleteDocuments(new Term("id", String.valueOf(id)));
		closeIndexWriter(indexWriter);
		generateIndexSearcher();
	}
	
	/**
	 * IndexWriter工厂
	 */
	public static IndexWriter getIndexWriter() throws IOException {
		IndexWriter indexWriter = null;
		Directory d = MMapDirectory.open(new File(lucenceIndex));
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_2, getAnalyzer());
		indexWriter = new IndexWriter(d, config);
		return indexWriter;
	}
	
	private static void closeIndexWriter(IndexWriter indexWriter) {
		if(indexWriter != null) {
			try {
				indexWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 重建索引
	 */
	public static void rebuild() throws IOException  {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ArticleServcie articleService =  (ArticleServcie) context.getBean("articleService"); 
		List<Article> articles = articleService.findAll();
		IndexWriter indexWriter = LucenceUtil.getIndexWriter();
		indexWriter.deleteAll();
		for(Article a : articles) {
			indexWriter.addDocument(a.getDocument());
		}
		indexWriter.close();
		System.out.println("共对 " + articles.size() + "条博文建立索引");
		context.close();
		//更新搜索器
		generateIndexSearcher();
	}
	
	/**
	 * 查询
	 */
	public static TopDocs query(Query query) throws IOException {
		IndexSearcher indexSearcher = LucenceUtil.getIndexSearcher();
		return indexSearcher.search(query, 100);
	}
	
	/**
	 * 测试
	 */
	public static void main(String[] args) throws IOException {
		//rebuild();
		Query query = new TermQuery(new Term("title", "天气"));
		TopDocs topDocs = getIndexSearcher().search(query, 100);
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		int n = 0;
		Document doc = null;
		for(ScoreDoc sd : scoreDocs) {
			n = sd.doc;
			doc = getIndexSearcher().doc(n);
			System.out.println(doc.get("date"));
		}
	}
}
