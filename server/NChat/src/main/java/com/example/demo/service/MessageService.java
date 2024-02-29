package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ReciveUserEmailDto;
import com.example.demo.model.Message;

public interface MessageService {
	public Message saveMessage(ReciveUserEmailDto receiveId );
	public List<Message> getMessage(String senderId,String receiveId) throws Exception;
	public List<Message> getAllTheMessage();
	public void readMessage(ReciveUserEmailDto reciveUserEmailDto);
	
	
}
