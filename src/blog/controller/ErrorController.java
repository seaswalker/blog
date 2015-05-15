package blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 转向错误页面，为了解决ajax提交无法返回的问题
 * @author skywalker
 *
 */
@Controller
public class ErrorController {

	@RequestMapping("/error")
	public String error() {
		return "error";
	}
	
}
