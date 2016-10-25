package com.parcel.repository;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.parcel.entity.User;

@Repository
public class UserRepositoryImpl implements UserRepository {
	private Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);
	private JdbcTemplate t;
	private final int INT_NULL = -1;
	
	@Autowired
	public UserRepositoryImpl(DataSource dataSource) {
		t = new JdbcTemplate(dataSource);
	}
	
	@Override
	public boolean join(User user) {
		logger.info("UserRepository join process");
		String sql = "INSERT INTO user (id, pw, phone, email) values(?,?,?,?)";
		try {
			t.update(sql, user.getId(), user.getPw(), user.getPhone(), user.getEmail());
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public int login(User user) {
		logger.info("UserRepository login process");
		String sql = "SELECT idx FROM user WHERE id=? AND pw=?";
		User temp;
		try {
			temp = t.queryForObject(sql, (rs,no)->{return new User(rs.getInt(1));}, user.getId(), user.getPw());
			return temp.getIdx();
		} catch(Exception e) {
			logger.error("login fail in UserRepository login process");
			return INT_NULL;
		}
		
	}

}
