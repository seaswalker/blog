package blog.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import blog.service.UserService;

/**
 * 关于界面
 * @author skywalker
 *
 */
@Controller
public class AboutController {
	
	@Resource(name = "userService")
	private UserService userService;

	@RequestMapping("/about")
	public String about(Model model) {
		//从用户表中拿到关于信息
		String about = userService.getAbout();
		model.addAttribute("about", about);
		return "about";
	}
	
}
