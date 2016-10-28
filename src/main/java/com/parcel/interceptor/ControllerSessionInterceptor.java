package com.parcel.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.parcel.service.UserService;

@Component
public class ControllerSessionInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		try {
			//세션에 idx 없으면 index로
			if(request.getSession().getAttribute("idx") == null ){
					response.sendRedirect("/");	
					return false;
			}
			int idx = (int)request.getSession().getAttribute("idx");
			
			request.getSession().setAttribute("userEntity", userService.getMainPageEntityForUserInfo(idx));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//세션에idx 있으면 원래대로
		
		
		return true;
	}
}
