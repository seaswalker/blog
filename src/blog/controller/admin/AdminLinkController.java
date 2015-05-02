package blog.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import blog.model.Link;
import blog.model.page.PageBean;
import blog.model.page.QueryResult;
import blog.service.LinkService;

/**
 * 链接管理
 * @author skywalker
 *
 */
@Controller
@RequestMapping("/admin/link")
public class AdminLinkController {

	@Resource(name = "linkService")
	private LinkService linkService;
	
	@RequestMapping("list")
	public String list(Model model, Integer pageCode) {
		int pageSize = 8;
		pageCode = (pageCode == null || pageCode < 1) ? 1 : pageCode;
		QueryResult<Link> queryResult = linkService.getSrollData("link", pageCode, pageSize);
		PageBean<Link> pageBean = new PageBean<Link>(queryResult.getRecords(), pageSize, pageCode, queryResult.getRecordCount());
		model.addAttribute("pageBean", pageBean);
		return "admin/link/list";
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("save")
	public String saveOrUpdate(Integer id, String name, String url, Model model) {
		Link link = new Link();
		if(id != null) {
			link.setId(id);
		}
		link.setName(name);
		link.setUrl(url);
		linkService.saveOrUpdate(link);
		model.addAttribute("message", "链接保存成功");
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
		model.addAttribute("link", linkService.findById(id));
		return "admin/link/saveUI";
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("delete")
	public String delete(Integer id, Model model) {
		if(id != null) {
			linkService.delete(id);
		}
		return "redirect:list.html";
	}
	
	/**
	 * 转向增加页面
	 */
	@RequestMapping("add")
	public String add() {
		return "admin/link/saveUI";
	}
	
}
