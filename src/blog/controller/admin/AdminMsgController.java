package blog.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import blog.model.Message;
import blog.service.MessageService;
import blog.util.DataUtil;

/**
 * 后台留言板管理
 * @author skywalker
 *
 */
@Controller
@RequestMapping("/admin/message")
public class AdminMsgController {
	
	@Resource(name = "messageService")
	private MessageService messageService;

	/**
	 * 转向修改页面
	 */
	@RequestMapping("/update")
	public String update(Model model) {
		//拿到留言板，留言板只有一个
		Message message = messageService.findById(1);
		model.addAttribute("message", message);
		return "admin/message/update";
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public String save(Integer id, String editorValue, Model model) {
		Message message = new Message();
		if(DataUtil.isValid(id)) {
			message.setId(id);
		}
		message.setContent(editorValue);
		messageService.saveOrUpdate(message);
		model.addAttribute("message", "保存成功");
		return "admin/message";
	}
	
}
