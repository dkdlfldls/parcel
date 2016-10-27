package com.parcel.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parcel.entity.Group;
import com.parcel.service.GroupService;

@Controller
public class GroupController {
	
	public static final int WRONG_PW = 0;
	public static final int ERROR = 1;
	public static final int SUCCESS = 2;
	
	@Autowired
	private GroupService groupService;
	

	@RequestMapping(value="/group/makeGroupCode", method=RequestMethod.POST)
	@ResponseBody
	public String makeGroupCode() {
		
		String code = groupService.makeUniquenessOfGroupCode();
		System.out.println(code);
		if (code == null) {
			return "실패";
		} else {
			return code;
		}
		//코드를 만든다음 user_group 테이블에 있는지 확인하고 없으면 보내주고 있으면 다시 만들고만들고...하다가 보내준다
		
	}
	@RequestMapping(value="/group/addGroup", method=RequestMethod.POST)
	@ResponseBody
	public String addGroup(@RequestBody Group group, HttpSession session) {
		//그룹을 추가하고 (코드 중복으로 인해 실패할 수 있음 -->재수없게 동시에 두 코드가 중복되서 누군가 먼저 넣은경우)
		System.out.println(group.toString());
		group.setManager((int)session.getAttribute("idx"));
		if(groupService.addGroup(group)) {
			return "성공";
		} else {
			return "실패";
		}
		
	}
	@RequestMapping(value="/group/deleteGroup", method=RequestMethod.POST)
	@ResponseBody
	public int deleteGroup(@RequestBody Group group, HttpSession session) {
		//스프링 시큐리티로 꼭 사용자 확인 해줘야한다. 안그러면 그냥 삭제 막 할 수 있다.ㄲ꼬꼬꼬꼬꼮꼬꼮꼮꼮꼮꼮
		return groupService.deleteGroup(group);
		
	}
}
