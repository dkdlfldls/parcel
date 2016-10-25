package com.parcel.service;

import com.parcel.entity.User;

public interface UserService {
	public boolean join(User user);
	public int login(User user);
}
