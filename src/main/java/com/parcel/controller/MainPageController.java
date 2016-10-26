package com.parcel.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.parcel.entity.MainPageEntity;
import com.parcel.entity.User;
import com.parcel.service.UserService;

@Controller
public class MainPageController {
	
	private Logger logger = LoggerFactory.getLogger(MainPageController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String getIndexPage(HttpSession session) {
		if (session.getAttribute("idx") != null) {
			return "redirect:/main";
		} else {
			
			return "../index";
		}
		
	}
	
	@RequestMapping("/main")
	public String getMainPage(HttpSession session, Model model) {
		int idx;
		
		if (session.getAttribute("idx") != null) {
			idx = (int) session.getAttribute("idx");
			model.addAttribute("userEntity", userService.getMainPageEntityForUserInfo(idx));  //요놈 가능하다면 aop로 빼버려야 겠다.
			model.addAttribute("listEntity", userService.getMainPageEntityList(idx));
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
	@RequestMapping("/groupInfo")
	public String getGroupInfo() {
		//몇몇 파라미터 받아서 페이지를 그릴 수 있어야 되는데 일단은 연결만
		return "/groupManager/groupInfo";
	}
}
