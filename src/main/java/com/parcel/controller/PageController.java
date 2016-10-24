package com.parcel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.parcel.service.TestService;

@Controller
public class PageController {
	
	@Autowired
	private TestService service;
	
	@RequestMapping("/")
	public String getIndexPage() {
		
		return "../index";
	}
	
	@RequestMapping("/main")
	public String getMainPage() {
		service.addState("aa");
		return "main";
	}
	
	@RequestMapping("/login")
	public String getLoginPage() {
		return "user/login";
	}
	
	@RequestMapping("join")
	public String getJoinPage() {
		return "user/join";
	}
}
