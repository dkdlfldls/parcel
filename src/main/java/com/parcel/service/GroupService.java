package com.parcel.service;

import com.parcel.entity.Group;

public interface GroupService {
	public Group getGroupInfoForProductInfo(int pidx);
	
	public String makeUniquenessOfGroupCode();

	public boolean addGroup(Group group);

	public int deleteGroup(Group group);
}
