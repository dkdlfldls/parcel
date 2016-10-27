package com.parcel.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parcel.entity.MainPageEntity;
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
		
		logger.info("=====join process in UserServiceImpl=====");
		if (userRepository.join(user)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int login(User user) {
		// TODO Auto-generated method stub
		logger.info("=====login process in UserServiceImpl=====");
		int idx = userRepository.login(user);
		return idx;
	}

	@Override
	public User getUser(int idx) {
		logger.info("=====getUser process in UserServiceImpl=====");
		
		return userRepository.getUser(idx);
	}

	@Override
	public List<MainPageEntity> getMainPageEntityList(int idx) {
		logger.info("=====getUser getMainPageEntityList in UserServiceImpl=====");
		return userRepository.getMainPageEntityList(idx);
	}

	@Override
	public MainPageEntity getMainPageEntityForUserInfo(int idx) {
		logger.info("=====getUser getMainPageEntityForUserInfo in UserServiceImpl=====");
		
		return userRepository.getMainPageEntityForUserInfo(idx);
	}

}