package blog.controller;

import java.text.DateFormat;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import blog.model.Article;
import blog.service.ArticleServcie;

/**
 * 博文控制器
 * @author skywalker
 *
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
	
	@Resource(name = "articleService")
	private ArticleServcie articleService;

	/**
	 * 显示一篇博文
	 */
	@RequestMapping
	public String view(Integer id, Model model) {
		if(id == null || id < 1) {
			return "redirect:index.html";
		}
		Article article = articleService.findById(id);
		//点击量加一
		articleService.addClickCount(id);
		//获取上一篇和下一篇博文
		String time = DateFormat.getDateTimeInstance().format(article.getCreateTime());
		String hql = "from Article where createtime > ? order by createtime asc";
		Article pre = (Article) articleService.query(hql, time);
		hql = "from Article where createtime < ? order by createtime desc";
		Article next = (Article) articleService.query(hql, time);
		model.addAttribute("pre", pre);
		model.addAttribute("next", next);
		model.addAttribute("article", article);
		return "article";
	}
	
}
