package com.parcel.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.parcel.service.UserService;

@Controller
public class MainPageController {
	
	private Logger logger = LoggerFactory.getLogger(MainPageController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String getIndexPage(HttpSession session) {
		
		
		if (session.getAttribute("idx") != null) {
			return "redirect:/parcel/main";
		} else {
			
			return "../index";
		}
		
	}
	
	@RequestMapping("/parcel/main")
	public String getMainPage(HttpSession session, Model model) {
		int idx = (int) session.getAttribute("idx");
		model.addAttribute("listEntity", userService.getMainPageEntityList(idx));
		return "/main/main";
		
	}
	
	@RequestMapping("/join")
	public String getJoinPage() {
		return "/user/join";
	}
	
	@RequestMapping("/intro/main")
	public String getIntroPage() {
		return "/intro/introMain";
	}

}
