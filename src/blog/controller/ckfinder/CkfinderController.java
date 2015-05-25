package blog.controller.ckfinder;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import blog.util.DataUtil;

/**
 * 打开ckfinder的静态html页面
 * @author skywalker
 *
 */
@Controller
public class CkfinderController {

	@RequestMapping("/ckfinder/ckfinder")
	@ResponseBody
	public void ckfinder(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String rootPath = request.getServletContext().getRealPath("/");
		String html = rootPath + "/" + "ckfinder/ckfinder.html";
		String content = DataUtil.readFormFile(html);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write(content);
		out.close();
	}
	
}
