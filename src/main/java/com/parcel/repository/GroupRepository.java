package com.parcel.repository;

import java.util.List;

import com.parcel.entity.Group;
import com.parcel.entity.GroupMember;
import com.parcel.entity.User;

public interface GroupRepository {
	
	public Group findGroupByProductIdx(int pidx);
	public List<User> findUserListByProductIdx(int pidx);
	public Group findGroupByCode(String code);
	public int insertGroup(Group group);
	public int insertGroupMember(GroupMember member);
	public boolean isValidPw(Group group);
	public int deleteGroupMemberByGroup(int idx) throws Exception;
	public int deleteGroupByIdx(int idx) throws Exception;
	public int insertGroupMemberByCodeAndPw(String code, String pw, int joiner);
	public GroupMember findGroupMemberByCodeAndMember(String code, int joiner);
	public List<Group> findGroupListByUserIdx(int uidx);
	public int deleteGroupMemberByGroupAndUser(int gidx, int uidx);
	
	
}
