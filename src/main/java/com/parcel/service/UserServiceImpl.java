package com.parcel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parcel.entity.User;
import com.parcel.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public boolean join(User user) {
		// TODO Auto-generated method stub
		
		logger.info("=====join process in service=====");
		if (userRepository.join(user)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int login(User user) {
		// TODO Auto-generated method stub
		logger.info("=====login process in service=====");
		int idx = userRepository.login(user);
		return idx;
	}

}
