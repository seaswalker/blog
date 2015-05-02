package blog.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import blog.model.Message;
import blog.service.MessageService;

/**
 * 留言板
 * @author skywalker
 *
 */
@Controller
@RequestMapping("/message")
public class MessageController {
	
	@Resource(name = "messageService")
	private MessageService messageService;

	/**
	 * 显示留言板
	 */
	@RequestMapping
	public String show(Model model) {
		Message message = messageService.findById(1);
		model.addAttribute("message", message);
		return "message";
	}
	
}
