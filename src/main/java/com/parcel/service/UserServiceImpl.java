package com.parcel.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.parcel.entity.MainPageEntity;
import com.parcel.entity.User;
import com.parcel.repository.UserRepository;
import com.parcel.repository.UserRepositoryImpl;
import com.parcel.util.DataSecurity;

@Service
public class UserServiceImpl implements UserService{
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DataSecurity dataSecurity;
	
	@Override
	public boolean join(User user) {
		//비밀번호 암호화
		user.setPw(dataSecurity.encrypt(user.getPw()));
		if (userRepository.join(user)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int login(User user) {
		User tempUser = userRepository.findUserById(user.getId());
		if (tempUser != null && dataSecurity.check(user.getPw(), tempUser.getPw())) {
			return tempUser.getIdx();
		} else {
			return UserRepositoryImpl.INT_NULL;
		}
	}

	@Override
	public User getUser(int idx) {
		
		return userRepository.getUser(idx);
	}

	@Override
	public List<MainPageEntity> getMainPageEntityList(int idx) {
		return userRepository.getMainPageEntityList(idx);
	}

	@Override
	public MainPageEntity getMainPageEntityForUserInfo(int idx) {
		
		return userRepository.getMainPageEntityForUserInfo(idx);
	}

}
