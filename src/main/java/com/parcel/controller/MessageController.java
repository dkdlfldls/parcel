package com.parcel.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parcel.entity.Message;
import com.parcel.service.MessageService;

@Controller
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	
	@RequestMapping("/message/info")
	public String getMessageInfo(Model model, HttpSession session) {
		
		int idx = (int)session.getAttribute("idx");
		List<Message> list = messageService.getMessageList(idx);
		if (list == null || list.isEmpty()) {
			model.addAttribute("isEmpty", true);
		} else {
			model.addAttribute("isEmpty", false);
		}
		model.addAttribute("messageList", list);
		
		return "/messageManager/MessageInfo";
	}
	
	@RequestMapping(value="/message/check", method=RequestMethod.POST)
	@ResponseBody
	public String checkMessage(@RequestBody Message message) {
		System.out.println(message.getIdx() + "@@@@@");
		boolean result = messageService.checkMessage(message.getIdx());
		if (result) {
			return "success";
		} else {
			return "fail";
		}
		
	}
}
