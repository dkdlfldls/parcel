package com.parcel.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

	@RequestMapping("/admin/main")
	public String getAdminMainPage(HttpSession session, Model model) {
		return "admin/main";
	}
}
