package com.parcel.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public String joinAndValidate(RedirectAttributes redirectAttributes, @Valid User user, BindingResult result) {
		
		userService.join(user);
		return "redirect:/";
	}
	
	@RequestMapping(value="/user/login", method=RequestMethod.POST)
	public String loginAndValidate(HttpSession session, @Valid User user, BindingResult result) {
		int idx;
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
