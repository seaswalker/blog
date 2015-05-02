package blog.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import blog.model.User;
import blog.service.UserService;

/**
 * 关于我们
 * @author skywalker
 *
 */
@Controller
@RequestMapping("/admin/about")
public class AdminAboutController {
	
	@Resource(name = "userService")
	private UserService userService;

	/**
	 * 显示信息
	 */
	@RequestMapping("/update")
	public String update(HttpSession session, Model model) {
		//取出关于信息
		User user = (User) session.getAttribute("user");
		String about = user.getAboutUs();
		model.addAttribute("about", about);
		return "admin/about/view";
	}
	
	/**
	 * 保存信息
	 */
	@RequestMapping("/save")
	public String save(String editorValue, HttpSession session, Model model) {
		String sql = "update user set aboutus = '" + editorValue + "'";
		userService.executeSql(sql);
		//保存进session
		User user = (User) session.getAttribute("user");
		user.setAboutUs(editorValue);
		model.addAttribute("message", "保存成功");
		return "admin/message";
	}
	
}
