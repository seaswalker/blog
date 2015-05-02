package blog.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import blog.model.Article;
import blog.model.page.PageBean;
import blog.model.page.QueryResult;
import blog.service.ArticleServcie;
import blog.util.DataUtil;
import blog.util.StringUtil;

/**
 * 转向主页
 * @author skywalker
 *
 */
@Controller
@RequestMapping("/index")
public class IndexController {
	
	@Resource(name = "articleService")
	private ArticleServcie articleServcie;

	@RequestMapping
	public String index(Integer page, Integer date, Integer cy, Integer tag, String search, Model model) {
		//获取分页的博文
		int pageSize = 6;
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
		}else if(!StringUtil.isEmpty(search)) {
			//搜索内容
			sb.append(" and title like ?");
			params.add("%" + search + "%");
			//params.add("'%" + search + "%'");出错
		}
		QueryResult<Article> queryResult = articleServcie.getSrollData("article", sb.toString(), params, orderbys, page, pageSize);
		PageBean<Article> pageBean = new PageBean<Article>(queryResult.getRecords(), pageSize, page, queryResult.getRecordCount());
		model.addAttribute("pageBean", pageBean);
		
		//参数再次放入Model
		model.addAttribute("date", date);
		model.addAttribute("cy", cy);
		model.addAttribute("tag", tag);
		model.addAttribute("search", search);
		return "index";
	}
	
}
