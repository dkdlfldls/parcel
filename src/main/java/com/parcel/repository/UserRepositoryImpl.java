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
			e.printStackTrace();
			return INT_NULL;
		}
		
	}

	@Override
	public User getUser(int idx) {
		logger.info("UserRepository getUser process");
		String sql = "SELECT * FROM user WHERE idx=? AND state=1";
		try {
			return t.queryForObject(sql, (rs, no)->{
				User temp = new User();
				temp.setIdx(rs.getInt(1));
				temp.setId(rs.getString(2));
				temp.setPw(rs.getString(3));
				temp.setPhone(rs.getString(4));
				temp.setEmail(rs.getString(5));
				temp.setWeb_authority(rs.getInt(6));
				temp.setState(rs.getInt(7));
				temp.setName(rs.getString(8));
				return temp;
			}, idx);
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public List<MainPageEntity> getMainPageEntityList(int idx) {
		logger.info("UserRepository getMainPageEntityList process");
		String sql = "SELECT p.public_name as pname, p.is_open as isopen, (SELECT count(gm.idx) FROM user_group ug, group_member gm WHERE ug.product = p.idx AND ug.idx=gm.group) as countg, p.idx as pidx, u.name "    
				+ "FROM product p, user u " 
				+ "WHERE (p.idx in (SELECT ug.product FROM user_group ug WHERE ug.idx in (SELECT gm.group FROM parcel.group_member gm WHERE gm.member=?)) OR p.registrant=?) AND u.idx=p.registrant";
		
		//자기 택배함 은 물론 다른 사람의 택배함 그룹도 나와야한다.
		
		try {
			return t.query(sql, (rs,no)->{
				MainPageEntity temp = new MainPageEntity();
				temp.setPname(rs.getString(1));
				temp.setIsopen(rs.getInt(2));
				temp.setCountg(rs.getInt(3));
				temp.setPidx(rs.getInt(4));
				temp.setName(rs.getString(5));
				return temp;
			}, idx, idx);
		} catch(Exception e) {
			return null;
		}
		
	}

	@Override
	public MainPageEntity getMainPageEntityForUserInfo(int idx) {
		// TODO Auto-generated method stub
		logger.info("UserRepository getMainPageEntityForUserInfo process");
		String sql = "SELECT u.name, count(m.idx) as countm, u.idx " +  
				"FROM user u, message m " +   
				"WHERE u.idx=? AND u.idx=m.receiver AND m.show=0 AND u.state=1";
		try {
			 return t.queryForObject(sql,(rs,no)->{
					MainPageEntity m = new MainPageEntity();
					m.setName(rs.getString(1));
					m.setCountm(rs.getInt(2));
					m.setIdx(rs.getInt(3));
					return m;
				} ,idx);
			
		} catch(Exception e) {
			return null;
		}
		
	}
	
	
	
	

}
