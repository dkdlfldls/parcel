package com.parcel.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.parcel.service.TestService;

@Controller
public class PageController {
	
	private Logger logger = LoggerFactory.getLogger(PageController.class);
	
	@Autowired
	private TestService service;
	
	@RequestMapping("/")
	public String getIndexPage(HttpSession session) {
		if (session.getAttribute("idx") != null) {
			return "redirect:/main";
		} else {
			
			return "../index";
		}
		
	}
	
	@RequestMapping("/main")
	public String getMainPage(HttpSession session) {
		if ((int)session.getAttribute("idx") > 0) {
			return "/main/main";
		} else {
			return "/";
		}
		
	}
	
	@RequestMapping("join")
	public String getJoinPage() {
		return "/user/join";
	}
	
	@RequestMapping("/parcelInfo")
	public String getParcelInfo() {
		//몇몇 파라미터 받아서 페이지를 그릴 수 있어야 되는데 일단은 연결만
		return "/parcelManager/parcelInfo";
	}
}
