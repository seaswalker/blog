package blog.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import blog.model.Article;
import blog.model.Message;
import blog.model.Reply;
import blog.service.ArticleServcie;
import blog.service.ReplyService;
import blog.util.DataUtil;
import blog.util.StringUtil;

/*
 * 回复控制器
 */
@Controller
@RequestMapping("/reply")
public class ReplyController {
	
	@Resource(name = "replyService")
	private ReplyService replyService;
	@Resource(name = "articleService")
	private ArticleServcie articleService;

	/**
	 * 发表评论
	 */
	@RequestMapping("/save")
	public void save(Integer aid, Integer pid, Integer mid, String nickname, String email, String homepage, String content, HttpServletResponse response) throws IOException {
		if(StringUtil.isEmpty(nickname) || StringUtil.isEmpty(email) || StringUtil.isEmpty(content)) {
			response.sendRedirect("error.html");
		}
		Reply reply = new Reply(nickname, content, email, homepage);
		if(DataUtil.isValid(aid)) {
			//博文的评论数量加一
			articleService.addReplyCount(aid);
			Article article = new Article();
			article.setId(aid);
			reply.setArticle(article);
		}
		if(DataUtil.isValid(pid)) {
			reply.setParent(new Reply(pid));
		}
		if(DataUtil.isValid(mid)) {
			reply.setMessage(new Message(mid));
		}
		reply.setHeadpath("images/default-avatar.png");
		replyService.saveOrUpdate(reply);
	}
	
	/**
	 * 返回所有评论
	 * aid和mid分别指文章的id和留言的id
	 */
	@RequestMapping("/list")
	public void list(Integer aid, Integer mid, Model model, HttpServletResponse response) throws IOException {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		if(!DataUtil.isValid(aid) && !DataUtil.isValid(mid)) {
			writer.write("[]");
			return;
		}
		List<Reply> replies = null;
		if(DataUtil.isValid(aid)) {
			replies = replyService.findAll(true, aid);
		}else {
			replies = replyService.findAll(false, mid);
		}
		model.addAttribute("replies", replies);
		//转为json数组
		StringBuilder sb = new StringBuilder("[");
		Reply parent = null;
		for(Reply reply : replies) {
			parent = reply.getParent();
			int parentId = 0;
			if(parent != null) {
				parentId = parent.getId();
			}
			sb.append("{'id':'").append(reply.getId())
				.append("','nickname':'").append(reply.getNickname())
				.append("','headpath':'").append(reply.getHeadpath())
				.append("','replytime':'").append(reply.getReplytime())
				.append("','parent':'").append(parentId)
				.append("','content':'").append(reply.getContent()).append("'},");
		}
		if(DataUtil.isValid(replies)) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("]");
		writer.write(sb.toString());
	}
	
}
