package com.parcel.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.parcel.controller.GroupController;
import com.parcel.entity.Group_member;
import com.parcel.entity.User;
import com.parcel.entity.User_group;
import com.parcel.repository.GroupRepository;
import com.parcel.util.CodeMaker;
import com.parcel.util.DataSecurity;
import com.parcel.util.LogMaker;
import com.parcel.util.LogProperties;

@Service
public class GroupServiceImpl implements GroupService {

	private final int TRY_COUNT = 10;
	private final int GroupCodeSize = 45;
	
	@Autowired
	private CodeMaker codeMaker;
	
	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private DataSecurity dataSecurity;
	
	@Autowired
	private LogService logService;
	@Autowired
	private LogProperties prop;
	@Autowired
	private LogMaker logMaker;
	
	@Override
	public User_group getGroupInfoForProductInfo(int pidx) {
		// TODO Auto-generated method stub
		User_group group = groupRepository.findGroupByProductIdx(pidx);
		List<User> userList = groupRepository.findUserListByProductIdx(pidx);
		if (!userList.isEmpty()) {
			group.setGroupUserList(userList);
		}
		
		return group;
	}

	@Override
	public String makeUniquenessOfGroupCode() {
		// TODO Auto-generated method stub
		String codeTemp;
		User_group temp;
		for (int i = 0; i < TRY_COUNT; i++) {
			codeTemp = codeMaker.makeCode(GroupCodeSize);
			if(  (temp = groupRepository.findGroupByCode(codeTemp)) == null ) {
				return codeTemp;
			}
		}
		return null;
	}

	@Transactional
	@Override
	public boolean addGroup(User_group group) {
		int result = groupRepository.insertGroup(group);
		logService.addLog(logMaker.addGroup(group.getProduct(), group.getManager(), group.getGroup_name())  , group, prop.getInt("addGroup"));
		int groupIdx;
		if (result > 0) {
			//성공 했으면 그 그룹에 자기 자신을 추가해줘야 한다.
			groupIdx = groupRepository.findGroupByCode(group.getCode()).getIdx();
			Group_member member = new Group_member();
			member.setGroup(groupIdx);
			member.setMember(group.getManager());
			//그룹에 자기자신 추가
			groupRepository.insertGroupMember(member);
			logService.addLog(logMaker.joinGroup(member.getMember(), group.getProduct(), member.getGroup())  , member, prop.getInt("joinGroup"));
			return true;
		} else {
			return false;
		}
	}

	@Transactional
	@Override
	public int deleteGroup(User_group group) {
		//group idx, pw
		//0. pw를 확인한다.
		int delGroupMemberCnt;
		int delGroupCnt;
		System.out.println(group.toString());
		try {
			if(groupRepository.isValidPw(group)) {
				//groupMember를 싹다지운다.
				delGroupMemberCnt = groupRepository.deleteGroupMemberByGroup(group.getIdx());
				if (delGroupMemberCnt <= 0) {
					throw new Exception();
				}
				//user group를 싹다 지운다.
				delGroupCnt = groupRepository.deleteGroupByIdx(group.getIdx());
				if (delGroupCnt <= 0) {
					throw new Exception();
				}
				logService.addLog(logMaker.deleteGroup(group.getIdx()), group, prop.getInt("groupDelete"));
				return GroupController.SUCCESS;
			} else {
				return GroupController.WRONG_PW;
			}
		} catch (Exception e) {
			System.out.println("deleteGroup error");
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); //try catch 안에서 트랜잭션 롤백 시키는 방법
			return GroupController.ERROR;
		}
	}

	@Override
	public boolean joinGroup(String code, String pw, int joiner)  {
		//가입을 확인한다 find메서드 뭐 써서 나오면 있는거니까
		Group_member member = groupRepository.findGroupMemberByCodeAndMember(code, joiner); 
		if (member == null) {
			int result = groupRepository.insertGroupMemberByCodeAndPw(code, pw, joiner);
			if (result > 0 ) {
				User_group group = groupRepository.findGroupByCode(code);
				logService.addLog(logMaker.joinGroup(joiner, group.getProduct(), group.getIdx())  , group, prop.getInt("joinGroup"));
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
		
		
	}

	@Override
	public List<User_group> getGroupList(int uidx) {
		List<User_group> list = groupRepository.findGroupListByUserIdx(uidx);
		System.out.println(list);
		if(list == null) {
			return new ArrayList<User_group>();
		} else {
			return list;
		}
	}

	@Override
	public boolean dropGroup(int gidx, int uidx) {
		if (groupRepository.deleteGroupMemberByGroupAndUser(gidx, uidx) > 0) {
			
			logService.addLog(logMaker.dropGroup(uidx, gidx), null, prop.getInt("dropGroup"));
			return true;
		} else {
			return false;
		}
	}

}
