package com.parcel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parcel.entity.Message;
import com.parcel.repository.MessageRepository;
import com.parcel.util.LogMaker;
import com.parcel.util.LogProperties;

@Service
public class MessageServiceImpl implements MessageService {
	
	public static final int SHOW = 1;
	public static final int NOT_SHOW = 0;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private LogService logService;
	@Autowired
	private LogProperties prop;
	@Autowired
	private LogMaker logMaker;
	

	@Override
	public List<Message> getMessageList(int idx) {
		
		return messageRepository.findMessageListByReceiver(idx);
	}

	@Override
	public boolean checkMessage(int idx) {
		int result = messageRepository.updateShowByIdx(idx, SHOW);
		if (result > 0) {
			Message m = messageRepository.findMEssageByIdx(idx);
			logService.addLog(logMaker.checkMessage(m.getReceiver(), m.getIdx()), null, prop.getInt("checkMessage"));
			return true;
		} else {
			return false;			
		}
	}
}
