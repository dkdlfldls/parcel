package com.parcel.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.parcel.entity.Group;
import com.parcel.service.GroupService;

@Controller
public class GroupController {
	
	public static final int WRONG_PW = 0;
	public static final int ERROR = 1;
	public static final int SUCCESS = 2;
	
	@Autowired
	private GroupService groupService;
	
	@RequestMapping("/groupInfo")
	public String getGroupInfo(HttpSession session, Model model) {
		
		List<Group> list = groupService.getGroupList((int)session.getAttribute("idx"));
		model.addAttribute("groupList", list);
		//몇몇 파라미터 받아서 페이지를 그릴 수 있어야 되는데 일단은 연결만
		return "/groupManager/groupInfo";
	}

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
	public String addGroupAndValidate(HttpSession session, @RequestBody @Valid Group group, BindingResult result) {
		//그룹을 추가하고 (코드 중복으로 인해 실패할 수 있음 -->재수없게 동시에 두 코드가 중복되서 누군가 먼저 넣은경우)
		System.out.println(group.toString());
		group.setManager((int)session.getAttribute("idx"));
		if(groupService.addGroup(group)) {
			return "성공";
		} else {
			return "false";
		}
		
	}
	@RequestMapping(value="/group/deleteGroup", method=RequestMethod.POST)
	@ResponseBody
	public int deleteGroup(@RequestBody Group group, HttpSession session) {
		return groupService.deleteGroup(group);
		
	}
	
	@RequestMapping("/group/joinGroup")
	public String getJoinGroupPage(@RequestParam(name="msg", required=false) String msg, Model model) {
		
		if (msg != null) {
			model.addAttribute("msg", msg);
		}
		
		return "/groupManager/groupJoinPage";
	}
	
	@RequestMapping(value="/group/joinGroup", method=RequestMethod.POST)
	public String joinGroupAndValidate(HttpSession session, RedirectAttributes model, @Valid Group group, BindingResult result) {
		if (groupService.joinGroup(group.getCode(), group.getPw(), (int)session.getAttribute("idx"))) {
			return "redirect:/main";
		} else {
			model.addAttribute("msg", "check your input");
			return "redirect:";
		}
	}
	
	@RequestMapping(value="/group/drop/{gidx}")
	public String dropGroup(@PathVariable int gidx, HttpSession session) {
		boolean r = groupService.dropGroup(gidx, (int)session.getAttribute("idx"));
		
		return "redirect:/groupInfo";
	}
}
