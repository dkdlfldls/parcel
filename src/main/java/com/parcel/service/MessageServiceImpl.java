package com.parcel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parcel.entity.Message;
import com.parcel.repository.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService {
	
	public static final int SHOW = 1;
	public static final int NOT_SHOW = 0;
	
	@Autowired
	private MessageRepository messageRepository;

	@Override
	public List<Message> getMessageList(int idx) {
		
		return messageRepository.findMessageListByReceiver(idx);
	}

	@Override
	public boolean checkMessage(int idx) {
		int result = messageRepository.updateShowByIdx(idx, SHOW);
		if (result > 0) {
			return true;
		} else {
			return false;			
		}
	}
}
