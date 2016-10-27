package com.parcel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parcel.entity.Group;
import com.parcel.entity.GroupMember;
import com.parcel.entity.User;
import com.parcel.repository.GroupRepository;
import com.parcel.util.CodeMaker;

@Service
public class GroupServiceImpl implements GroupService {

	private final int TRY_COUNT = 10;
	private final int GroupCodeSize = 45;
	
	@Autowired
	private CodeMaker codeMaker;
	
	@Autowired
	private GroupRepository groupRepository;
	
	@Override
	public Group getGroupInfoForProductInfo(int pidx) {
		// TODO Auto-generated method stub
		Group group = groupRepository.findGroupByProductIdx(pidx);
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
		Group temp;
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
	public boolean addGroup(Group group) {
		int result = groupRepository.insertGroup(group);
		int groupIdx;
		if (result > 0) {
			//성공 했으면 그 그룹에 자기 자신을 추가해줘야 한다.
			groupIdx = groupRepository.findGroupByCode(group.getCode()).getIdx();
			GroupMember member = new GroupMember();
			member.setGroup(groupIdx);
			member.setMember(group.getManager());
			groupRepository.insertGroupMember(member);
			return true;
		} else {
			return false;
		}
	}

	@Transactional
	@Override
	public void deleteGroup(Group group) {
		//group idx, pw
		//1. groupMember를 싹다 지운다.
		//2. group을 지운다.
		
	}

}
