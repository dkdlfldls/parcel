package com.parcel.repository;

import com.parcel.entity.User;

public interface UserRepository {
	
	public boolean join(User user);
	public int login(User user);
}
