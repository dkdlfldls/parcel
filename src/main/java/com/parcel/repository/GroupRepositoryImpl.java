package com.parcel.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.parcel.entity.Group;
import com.parcel.entity.GroupMember;
import com.parcel.entity.User;

@Repository
public class GroupRepositoryImpl implements GroupRepository {

	private JdbcTemplate t;
	private final int FAIL = 0;
	
	@Autowired
	public GroupRepositoryImpl(DataSource dataSource) {
		t = new JdbcTemplate(dataSource);
	}
	
	@Override
	public Group findGroupByProductIdx(int pidx) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM user_group ug WHERE ug.product=?";
		
		try {
			return t.queryForObject(sql, (rs,no)->{
				Group group = new Group();
				group.setIdx(rs.getInt("idx"));
				group.setGroup_name(rs.getString("group_name"));
				group.setManager(rs.getInt("manager"));
				group.setProduct(rs.getInt("product"));
				group.setPw(rs.getString("pw"));
				group.setCode(rs.getString("code"));
				return group;
			}, pidx);
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public List<User> findUserListByProductIdx(int pidx) {
		String sql ="SELECT u.name "
				+ "FROM user u "
				+ "WHERE u.idx = (SELECT gm.member FROM group_member gm WHERE gm.group=(SELECT ug.idx FROM user_group ug WHERE ug.product=?))";
		
		try {
			return t.query(sql, (rs, no)->{
				User user = new User();
				user.setname(rs.getString("name"));
				return user;
			}, pidx);
			
		} catch(Exception e) {
			return null;
		}
		
		
	}

	@Override
	public Group findGroupByCode(String code) {
		
		String sql = "SELECT * FROM user_group WHERE code=?";
		try {
			return t.queryForObject(sql, (rs,no)->{ //이거랑 똑같은 로우맵퍼 또생기면 그냥 클래스 만들어서 쓰자
				Group group = new Group();
				group.setIdx(rs.getInt("idx"));
				group.setGroup_name(rs.getString("group_name"));
				group.setManager(rs.getInt("manager"));
				group.setProduct(rs.getInt("product"));
				group.setPw(rs.getString("pw"));
				group.setCode(rs.getString("code"));
				return group;
			}, code);
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public int insertGroup(Group group) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO user_group (group_name, manager, product, pw, code) values(?, ?, ?, ?, ?)";
		try {
			return t.update(sql, group.getGroup_name(), group.getManager(), group.getProduct(), group.getPw(), group.getCode());
		} catch (Exception e) {
			return FAIL;
		}
		
	}

	@Override
	public int insertGroupMember(GroupMember member) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO `parcel`.`group_member` (`group`, `member`) VALUES (?, ?)";
		try {
			return t.update(sql, member.getGroup(), member.getMember());
		} catch (Exception e) {
			return FAIL;
		}
		
		
	}

	@Override
	public boolean isValidPw(Group group) {
		
		Group temp;
		String sql = "SELECT idx FROM user_group WHERE idx=? AND pw=?";
		try {
			temp = t.queryForObject(sql, (rs,no)->{
				Group g = new Group();
				g.setIdx(rs.getInt(1));
				return g;
			}, group.getIdx(), group.getPw());
		} catch (Exception e) {
			temp = null;
		}
		if (temp != null) {
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public int deleteGroupMemberByGroup(int idx) throws Exception {
		String sql = "DELETE FROM parcel.group_member WHERE parcel.group_member.group=?";
		return t.update(sql, idx);
	}

	@Override
	public int deleteGroupByIdx(int idx) throws Exception {
		String sql = "DELETE FROM user_group WHERE idx=?";
		return t.update(sql, idx);
	}

}
