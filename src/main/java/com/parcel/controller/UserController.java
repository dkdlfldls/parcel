package com.parcel.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.parcel.entity.User;
import com.parcel.service.UserService;

@Controller
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private final int INT_NULL = -1;

	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/join")
	public String joinProcess(User user, RedirectAttributes redirectAttributes) {
		
		userService.join(user);
		return "redirect:/";
	}
	
	@RequestMapping("/user/login")
	public String loginProcess(User user, HttpSession session) {
		int idx;
		logger.info("userController user info check");
		logger.info(user.toString());
		
		if ( (idx = userService.login(user)) == INT_NULL) {
			return "redirect:/";
		} else {
			session.setAttribute("idx", idx);
			return "redirect:/main";
		}
		
	}
	
	@RequestMapping("/user/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
	}
	
}
