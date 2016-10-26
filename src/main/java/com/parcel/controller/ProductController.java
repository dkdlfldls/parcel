package com.parcel.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parcel.entity.Product;
import com.parcel.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
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
}
