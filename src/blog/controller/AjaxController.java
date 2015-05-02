package blog.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import blog.model.Link;
import blog.model.Tag;
import blog.service.AjaxService;
import blog.service.LinkService;
import blog.service.TagService;
import blog.util.DataUtil;

/**
 * 用于ajax方式获取所有日期、类别、友情链接
 * @author skywalker
 *
 */
@Controller
@RequestMapping("/ajax")
@ResponseBody
public class AjaxController {
	
	@Resource(name = "ajaxService")
	private AjaxService ajaxService;
	@Resource(name = "tagService")
	private TagService tagService;
	@Resource(name = "linkService")
	private LinkService linkService;

	/**
	 * 返回日期
	 */
	@RequestMapping("/dates")
	public String dates() {
		String sql = "select distinct(date_format(createtime, '%Y%m')) from article";
		@SuppressWarnings("unchecked")
		List<String> dates = (List<String>) ajaxService.batchNativeQuery(sql, new Object[]{});
		//转为json数组
		StringBuilder sb = new StringBuilder("[");
		for(String date : dates) {
			sb.append("{'year':'").append(date.substring(0, 4))
				.append("','month':'").append(date.substring(4, 6)).append("'},");
		}
		if(DataUtil.isValid(dates)) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("]");
		return sb.toString();
	}
	
	/**
	 * 返回类别以及类别下博文的数量
	 */
	@RequestMapping("cy")
	public void category(HttpServletResponse response) throws IOException {
		String sql = "select c.id, c.name, count(a.id) from category c left join article a on c.id = a.categoryid group by c.id";
		@SuppressWarnings("unchecked")
		List<Object[]> result = (List<Object[]>) ajaxService.batchNativeQuery(sql, new Object[]{});
		//转为json数组
		StringBuilder sb = new StringBuilder("[");
		for(Object[] array : result) {
			sb.append("{'id':'").append(array[0])
			.append("','category':'").append(array[1])
			.append("','count':'").append(array[2]).append("'},");
		}
		if(DataUtil.isValid(result)) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("]");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(sb.toString());
	}
	
	/**
	 * 返回标签以及id
	 */
	@RequestMapping("/tag")
	public void tag(HttpServletResponse response) throws IOException {
		List<Tag> tags = tagService.findAll();
		//转为json数组
		StringBuilder sb = new StringBuilder("[");
		for(Tag tag : tags) {
			sb.append("{'id':'").append(tag.getId())
			.append("','name':'").append(tag.getName()).append("'},");
		}
		if(DataUtil.isValid(tags)) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("]");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(sb.toString());
	}
	
	/**
	 * 返回链接
	 */
	@RequestMapping("/links")
	public void links(HttpServletResponse response) throws IOException {
		List<Link> links = linkService.findAll();
		//转为json数组
		StringBuilder sb = new StringBuilder("[");
		for(Link link : links) {
			sb.append("{'name':'").append(link.getName())
			.append("','url':'").append(link.getUrl()).append("'},");
		}
		if(DataUtil.isValid(links)) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("]");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(sb.toString());
	}
	
}
