package blog.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import blog.model.Article;
import blog.model.Category;
import blog.model.page.PageBean;
import blog.model.page.QueryResult;
import blog.service.ArticleServcie;
import blog.service.CategoryService;

/**
 * 后台类别管理
 * @author skywalker
 *
 */
@Controller
@RequestMapping("admin/category")
public class AdminCategoryController {
	
	@Resource(name = "categoryService")
	private CategoryService categoryService;
	@Resource(name = "articleService")
	private ArticleServcie articleService;

	/**
	 * 列表
	 */
	@RequestMapping("list")
	public String list(Model model, Integer pageCode) {
		int pageSize = 8;
		pageCode = (pageCode == null || pageCode < 1) ? 1 : pageCode;
		QueryResult<Category> queryResult = categoryService.getSrollData("category", pageCode, pageSize);
		PageBean<Category> pageBean = new PageBean<Category>(queryResult.getRecords(), pageSize, pageCode, queryResult.getRecordCount());
		model.addAttribute("pageBean", pageBean);
		return "admin/category/list";
	}
	
	/**
	 * 转向增加页面
	 */
	@RequestMapping("add")
	public String add() {
		return "admin/category/saveUI";
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("save")
	public String saveOrUpdate(Integer id, String category, Model model) {
		Category newCategory = new Category();
		if(id != null) {
			newCategory.setId(id);
		}
		newCategory.setName(category);
		categoryService.saveOrUpdate(newCategory);
		model.addAttribute("message", "标签保存成功");
		return "admin/message";
	}
	
	/**
	 * 转向修改
	 */
	@RequestMapping("update")
	public String update(Integer id, Model model) {
		if(id == null) {
			return "";
		}
		model.addAttribute("category", categoryService.findById(id));
		return "admin/category/saveUI";
	}
	
	/**
	 * 判断列别下是否博文
	 * @throws IOException 
	 */
	@RequestMapping("check")
	public void check(Integer id, HttpServletResponse response) throws IOException {
		String hql = "from Article where category.id = ?";
		Article article = (Article) articleService.query(hql, new Object[]{id});
		String result = "";
		if(article == null) {
			result = "true";
		}else {
			result = "false";
		}
		PrintWriter out = response.getWriter();
		out.write(result);
		out.flush();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("delete")
	public String delete(Integer id, Model model) {
		if(id != null) {
			categoryService.delete(id);
		}
		model.addAttribute("message", "删除成功");
		return "admin/message";
	}
	
}
