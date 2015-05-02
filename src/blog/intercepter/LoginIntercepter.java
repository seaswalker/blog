package blog.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 登录拦截器
 * @author skywalker
 *
 */
public class LoginIntercepter extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String path = request.getServletPath();
		//如果是这些公共地址放行
		if(path.indexOf("admin") != -1 && !path.equals("/admin.html") && !path.equals("/admin/login.html")) {
			HttpSession session = request.getSession();
			Object userObj = session.getAttribute("user");
			if(userObj != null) {
				return true;
			}
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/admin.html");
			return false;
		}
		return true;
	}
	
}
