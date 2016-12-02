package com.parcel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UtilController {
	
	@RequestMapping(value="/util/false", method=RequestMethod.GET)
	public @ResponseBody boolean sendFalse() {
		return false;
	}
	@RequestMapping(value="/util/true", method=RequestMethod.GET)
	public @ResponseBody boolean sendTrue() {
		return true;
	}
}
