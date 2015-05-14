package blog.controller.admin;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import blog.model.Article;
import blog.model.Category;
import blog.model.page.PageBean;
import blog.model.page.QueryResult;
import blog.service.ArticleServcie;
import blog.service.CategoryService;
import blog.service.TagService;
import blog.util.DataUtil;
import blog.util.HTMLUtil;
import blog.util.StringUtil;

/**
 * 后台博文管理
 * @author skywalker
 *
 */
@Controller
@RequestMapping("/admin/article")
public class AdminArticleController {
	
	@Resource(name = "articleService")
	private ArticleServcie articleServcie;
	@Resource(name = "categoryService")
	private CategoryService categoryService;
	@Resource(name = "tagService")
	private TagService tagService;
	

	/**
	 * 列表
	 */
	@RequestMapping("list")
	public String list(Model model, Integer pageCode) {
		int pageSize = 8;
		pageCode = (pageCode == null || pageCode < 1) ? 1 : pageCode;
		HashMap<String, String> orderbys = new HashMap<String, String>();
		orderbys.put("CREATETIME", "desc");
		QueryResult<Article> queryResult = articleServcie.getSrollData("article", orderbys, pageCode, pageSize);
		PageBean<Article> pageBean = new PageBean<Article>(queryResult.getRecords(), pageSize, pageCode, queryResult.getRecordCount());
		model.addAttribute("pageBean", pageBean);
		return "admin/article/list";
	}
	
	/**
	 * 转向写博客页面
	 */
	@RequestMapping("write")
	public String write(Model model) {
		//准备所有类别
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		return "admin/article/write";
	}
	
	/**
	 * 保存博客
	 * @param summary 博文摘要，如果为空使用取出html标签后的前32个子
	 */
	@RequestMapping("save")
	public String save(Integer id, String title, String content, String summary, int categoryid, String tags) {
		Article article = new Article();
		if(id != null) {
			article.setId(id);
		}
		//转为纯为本
		String digest = StringUtil.isEmpty(summary) ? StringUtil.subString(HTMLUtil.HtmltoText(content), 32)
				: summary;
		article.setDigest(digest);
		article.setCategory(new Category(categoryid));
		article.setContent(content);
		article.setTitle(title);
		DataUtil.convertTags(article, tags, tagService);
		articleServcie.saveOrUpdate(article);
		return "redirect:/admin/right.html";
	}
	
	/**
	 * 转向修改页面
	 */
	@RequestMapping("edit")
	public String edit(Integer id, Model model) {
		//获取要修改的博文
		if(id != null) {
			Article article = articleServcie.findById(id);
			model.addAttribute("article", article);
			//准备所有类别
			List<Category> categories = categoryService.findAll();
			model.addAttribute("categories", categories);
		}
		return "admin/article/write";
	}
	
	/**
	 * 博文删除
	 */
	@RequestMapping("delete")
	public String delete(Integer id) {
		if(id != null) {
			articleServcie.delete(id);
		}
		return "redirect:list.html";
	}
	
}
