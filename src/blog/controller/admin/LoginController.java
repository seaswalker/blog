package blog.controller.admin;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import blog.model.User;
import blog.service.UserService;
import blog.util.StringUtil;

/**
 * 登录
 * @author skywalker
 *
 */
@Controller
@RequestMapping("/admin")
public class LoginController {
	
	@Resource(name = "userService")
	private UserService userService;
	
	@RequestMapping
	/**
	 * 转向登录页面
	 */
	public String loginUI() {
		return "admin/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String username, String password, Model model, HttpSession session) throws IOException {
		if(StringUtil.isEmpty(username)) {
			model.addAttribute("error", "请输入用户名");
			return "admin/login";
		}else if(StringUtil.isEmpty(password)) {
			model.addAttribute("error", "请输入密码");
			model.addAttribute("username", username);
			return "admin/login";
		}
		//检查用户
		User user = userService.login(username, StringUtil.md5(password));
		if(user == null) {
			model.addAttribute("error", "用户名或密码不正确");
			model.addAttribute("username", username);
			return "admin/login";
		}else {
			//user存入session
			session.setAttribute("user", user);
		}
		return "redirect:/admin/index.html";
	}
}
