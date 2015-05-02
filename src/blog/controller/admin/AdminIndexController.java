package blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台主页
 * @author skywalker
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminIndexController {
	
	@RequestMapping("/top")
	public String top() {
		return "admin/index/top";
	}
	
	@RequestMapping("/left")
	public String left() {
		return "admin/index/left";
	}
	
	@RequestMapping("/index")
	public String index() {
		return "admin/index/index";
	}
	
	@RequestMapping("/right")
	public String right() {
		return "admin/index/right";
	}

}
