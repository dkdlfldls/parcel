package com.parcel.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.parcel.entity.Message;

@Repository
public class MessageRepositoryImpl implements MessageRepository {

	private JdbcTemplate t;
	
	@Autowired
	public MessageRepositoryImpl(DataSource dataSource) {
		t = new JdbcTemplate(dataSource);
	}
	
	@Override
	public int insertMessageForGroupByProductIdx(Message message, int productIdx) {
		//productIdx를 가지고 user_group에서 user_group의 idx를 찾고 그 idx로 group_member에서 해당 group인 사람들에게 전부 메시지를 넣는다.
		String sql = "INSERT INTO message (receiver, message) "
				+ "(SELECT gm.member as receiver, ? as message "
						+ "FROM parcel.group_member gm "
						+ "WHERE gm.group = (SELECT ug.idx FROM parcel.user_group ug WHERE ug.product=?))";
		
		return t.update(sql, message.getMessage(), productIdx);
		
	}

	@Override
	public int insertMessageByUserIdx(Message message, int idx) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO message (receiver, message) values(?,?)";
		return t.update(sql, idx, message.getMessage());
	}

	@Override
	public List<Message> findMessageListByReceiver(int idx) {
		String sql ="SELECT * FROM message WHERE receiver=?";
		
		try {
			return t.query(sql, (rs, no)->{
				return new Message(
						rs.getInt(1),
						rs.getInt(2),
						rs.getString(3),
						rs.getTimestamp(4),
						rs.getInt(5),
						rs.getInt(6)
				);
			}, idx);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int updateShowByIdx(int idx, int show) {
		String sql = "UPDATE message SET message.show=? WHERE message.idx=?";
		try {
			return t.update(sql, show, idx);
		} catch (Exception e) {
			return 0;
		}
		
		
	}

}
