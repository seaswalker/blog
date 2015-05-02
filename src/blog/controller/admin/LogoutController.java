package blog.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import blog.util.CookieUtil;

/**
 * 退出
 * @author skywalker
 *
 */
@Controller
public class LogoutController {

	@RequestMapping("/admin/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("user");
		//删除cookie
		CookieUtil.removeCookie("user", response);
		return "redirect:/index.html";
	}
	
}
