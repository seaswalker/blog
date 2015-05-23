package blog.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.model.Article;
import blog.model.page.LucencePageBean;
import blog.model.page.PageBean;
import blog.model.page.QueryResult;
import blog.service.ArticleServcie;
import blog.util.DataUtil;
import blog.util.PropertyUtil;
import blog.util.StringUtil;

/**
 * 转向主页
 * @author skywalker
 *
 */
@Controller
public class IndexController {
	
	@Resource(name = "articleService")
	private ArticleServcie articleServcie;
	/*
	 * 搜索分页尺寸
	 */
	private static int pageSize = Integer.parseInt(PropertyUtil.getProperty("search.pageSize"));

	@RequestMapping("/index")
	public String index(Integer page, Integer date, Integer cy, Integer tag, Model model) {
		//获取分页的博文
		int pageSize = Integer.parseInt(PropertyUtil.getProperty("index.pageSize"));
		page = (!DataUtil.isValid(page)) ? 1 : page;
		//按照发表时间倒序排列
		HashMap<String, String> orderbys = new HashMap<String, String>();
		orderbys.put("createtime", "desc");
		//设置where条件
		StringBuilder sb = new StringBuilder(" where 1 = 1");
		List<Object> params = new ArrayList<Object>();
		if(DataUtil.isValid(date)) {
			sb.append(" and date_format(createtime,'%Y%m') = ?");
			//日期条件
			params.add(date + "");
		}else if(DataUtil.isValid(cy)) {
			//类别条件
			sb.append(" and categoryid = ?");
			params.add(cy);
		}else if(DataUtil.isValid(tag)) {
			//标签条件
			sb.append(" and id in (select articleid from article_tag where tagid = ?)");
			params.add(tag);
		}
		QueryResult<Article> queryResult = articleServcie.getSrollData("article", sb.toString(), params, orderbys, page, pageSize);
		PageBean<Article> pageBean = new PageBean<Article>(queryResult.getRecords(), pageSize, page, queryResult.getRecordCount());
		model.addAttribute("pageBean", pageBean);
		
		//参数再次放入Model
		model.addAttribute("date", date);
		model.addAttribute("cy", cy);
		model.addAttribute("tag", tag);
		return "index";
	}
	
	/**
	 * 博客搜索
	 * @param pn 页码
	 */
	@RequestMapping("/search")
	public String search(@RequestParam(value="pn", defaultValue="1") int pn, String search, Model model) throws Exception {
		if(!StringUtil.isEmpty(search)) {
			LucencePageBean<Article> pageBean = articleServcie.search(search, pn, pageSize);
			model.addAttribute("search", search);
			model.addAttribute("pageBean", pageBean);
		}
		return "search";
	}
	
}
