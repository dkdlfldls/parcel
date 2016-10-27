package com.parcel.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parcel.entity.Group;
import com.parcel.entity.Product;
import com.parcel.service.GroupService;
import com.parcel.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private GroupService groupService;
	
	@RequestMapping("/product/addPage")
	public String addProductPage(Model model) {
		
		model.addAttribute("machineList", productService.getMachineList());
		
		return "/parcelManager/addProductPage";
	}
	
	@RequestMapping(value="/product/addProduct", method=RequestMethod.POST)
	@ResponseBody
	public String addProduct(@RequestBody Product product, HttpSession session) {
		System.out.println("addProduct process");
		System.out.println(product.toString());
		
		product.setRegistrant((int)session.getAttribute("idx"));
		String message = productService.addProduct(product);
		
		System.out.println(message);
		return message;
		
	}
	
	@RequestMapping("/product/getProductInfo")
	public String getProductInfo(int pidx, Model model) {
		
		model.addAttribute("product", productService.getProductInfo(pidx));
		
		Group group = groupService.getGroupInfoForProductInfo(pidx);
		if (group == null) {
			model.addAttribute("hasGroup", false);
		} else {
			model.addAttribute("hasGroup", true);
			model.addAttribute("group",groupService.getGroupInfoForProductInfo(pidx));
		}
		/*
		 * 택배함 이름, 개폐상태, 등록자이름, , 등록한 시간, 택배함 코드
		 * 
		 * + 그룹 정보
		 * 그룹명 그룹 소속원
		 */
		
		return "/parcelManager/parcelInfo";
	}
	
	
}
