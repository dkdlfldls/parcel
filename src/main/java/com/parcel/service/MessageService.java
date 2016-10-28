package com.parcel.service;

import java.util.List;

import com.parcel.entity.Message;

public interface MessageService {

	List<Message> getMessageList(int idx);

	boolean checkMessage(int idx);

}
