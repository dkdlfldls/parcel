package com.parcel.repository;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.parcel.entity.Log;

@Repository
public class LogRepositoryImpl implements LogRepository {

	private Logger logger = LoggerFactory.getLogger(LogRepositoryImpl.class);
	private JdbcTemplate t;
	
	@Autowired
	public LogRepositoryImpl(DataSource dataSource) {
		t = new JdbcTemplate(dataSource);
	}
	
	@Override
	public int insertLog(Log log) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO log (user, user_group, group_member, machine, product, delivery_record, receive_record, message, log, detail) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?)";
		try {
			return t.update(sql, log.getUser(), log.getUser_group(), log.getGroup_member(), log.getMachine()
					, log.getProduct(), log.getDelivery_record(), log.getReceive_record(), log.getMessage(), log.getLog(), log.getDetail());
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			return 0;
		}
	}

	

}
