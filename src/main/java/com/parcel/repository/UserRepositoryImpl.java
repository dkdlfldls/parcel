package com.parcel.repository;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.parcel.entity.MainPageEntity;
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
		String sql = "SELECT idx FROM user WHERE id=? AND pw=? AND state=1";
		User temp;
		try {
			temp = t.queryForObject(sql, (rs,no)->{return new User(rs.getInt(1));}, user.getId(), user.getPw());
			return temp.getIdx();
		} catch(Exception e) {
			logger.error("login fail in UserRepository login process");
			return INT_NULL;
		}
		
	}

	@Override
	public User getUser(int idx) {
		logger.info("UserRepository getUser process");
		String sql = "SELECT * FROM user WHERE idx=? AND state=1";
		User temp;
		try {
			return temp = t.queryForObject(sql, User.class, idx);
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public List<MainPageEntity> getMainPageEntityList(int idx) {
		logger.info("UserRepository getMainPageEntityList process");
		String sql = "SELECT p.public_name as pname, p.is_open as isopen, count(gm.idx) as countg " + 
				"FROM product p, user_group g, group_member gm " + 
				"WHERE p.registrant = ? AND p.registrant=g.manager AND g.idx=gm.group";
		try {
			return t.query(sql, (rs,no)->{
				MainPageEntity temp = new MainPageEntity();
				temp.setPname(rs.getString(1));
				temp.setIsopen(rs.getInt(2));
				temp.setCountg(rs.getInt(3));
				return temp;
			}, idx);
		} catch(Exception e) {
			return null;
		}
		
	}

	@Override
	public MainPageEntity getMainPageEntityForUserInfo(int idx) {
		// TODO Auto-generated method stub
		logger.info("UserRepository getMainPageEntityForUserInfo process");
		String sql = "SELECT u.name, count(m.idx) as countm " +  
				"FROM user u, message m " +   
				"WHERE u.idx=? AND u.idx=m.receiver AND m.show=0 AND u.state=1";
		try {
			 return t.queryForObject(sql,(rs,no)->{
					MainPageEntity m = new MainPageEntity();
					m.setName(rs.getString(1));
					m.setCountm(rs.getInt(2));
					return m;
				} ,idx);
			
		} catch(Exception e) {
			return null;
		}
		
	}
	
	
	
	

}
