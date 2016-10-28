package com.parcel.repository;

import java.util.List;

import com.parcel.entity.Message;

public interface MessageRepository {

	public int insertMessageForGroupByProductIdx(Message message, int productIdx);

	public int insertMessageByUserIdx(Message message, int idx);

	public List<Message> findMessageListByReceiver(int idx);

	public int updateShowByIdx(int idx, int show);
	
}
