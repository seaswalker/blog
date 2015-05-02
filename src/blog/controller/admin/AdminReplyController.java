package blog.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import blog.model.Reply;
import blog.model.page.PageBean;
import blog.model.page.QueryResult;
import blog.service.ReplyService;

/**
 * 后台回复管理
 * @author skywalker
 *
 */
@Controller
@RequestMapping("admin/reply")
public class AdminReplyController {
	
	@Resource(name = "replyService")
	private ReplyService replyService;

	@RequestMapping("list")
	public String list(Model model, Integer pageCode) {
		int pageSize = 8;
		pageCode = (pageCode == null || pageCode < 1) ? 1 : pageCode;
		QueryResult<Reply> queryResult = replyService.getSrollData("reply", pageCode, pageSize);
		PageBean<Reply> pageBean = new PageBean<Reply>(queryResult.getRecords(), pageSize, pageCode, queryResult.getRecordCount());
		model.addAttribute("pageBean", pageBean);
		return "admin/reply/list";
	}
	
	/**
	 * 评论删除，注意删除子评论
	 */
	@RequestMapping("/delete")
	public String delete(Integer id) {
		if(id != null && id > 0) {
			replyService.delete(id);
		}
		return "redirect:list.html";
	}
	
}
