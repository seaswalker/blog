package blog.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import blog.model.Tag;
import blog.model.page.PageBean;
import blog.model.page.QueryResult;
import blog.service.TagService;

/**
 * 后台标签管理
 * @author skywalker
 *
 */
@Controller
@RequestMapping("admin/tag")
public class AdminTagController {
	
	@Resource(name = "tagService")
	private TagService tagService;

	@RequestMapping("list")
	public String list(Model model, Integer pageCode) {
		int pageSize = 8;
		pageCode = (pageCode == null || pageCode < 1) ? 1 : pageCode;
		QueryResult<Tag> queryResult = tagService.getSrollData("tag", pageCode, pageSize);
		PageBean<Tag> pageBean = new PageBean<Tag>(queryResult.getRecords(), pageSize, pageCode, queryResult.getRecordCount());
		model.addAttribute("pageBean", pageBean);
		return "admin/tag/list";
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("save")
	public String saveOrUpdate(Integer id, String tag, Model model) {
		Tag newTag = new Tag();
		if(id != null) {
			newTag.setId(id);
		}
		newTag.setName(tag);
		tagService.saveOrUpdate(newTag);
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
		model.addAttribute("tag", tagService.findById(id));
		return "admin/tag/saveUI";
	}
	
	/**
	 * 判断标签下是否有博文
	 * @throws IOException 
	 */
	@RequestMapping("check")
	public void check(Integer id, HttpServletResponse response) throws IOException {
		String sql = "select count(*) from article_tag where TAGID = ?";
		int count = ((BigInteger) tagService.nativeQuery(sql, id)).intValue();
		String result = "";
		if(count == 0) {
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
	public void delete(Integer id, Model model) {
		if(id != null) {
			tagService.delete(id);
		}
	}
	
	/**
	 * 转向增加页面
	 */
	@RequestMapping("add")
	public String add() {
		return "admin/tag/saveUI";
	}
	
}
