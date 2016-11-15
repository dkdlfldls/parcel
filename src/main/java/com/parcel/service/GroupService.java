package com.parcel.service;

import java.util.List;

import com.parcel.entity.User_group;

public interface GroupService {
	public User_group getGroupInfoForProductInfo(int pidx);
	
	public String makeUniquenessOfGroupCode();

	public boolean addGroup(User_group group);

	public int deleteGroup(User_group group);

	public boolean joinGroup(String code, String pw, int joiner) ;

	public List<User_group> getGroupList(int uidx);

	public boolean dropGroup(int gidx, int uidx);
}
