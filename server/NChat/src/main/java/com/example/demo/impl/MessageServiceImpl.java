package com.example.demo.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ReciveUserEmailDto;
import com.example.demo.model.Message;
import com.example.demo.model.UserEntity;
import com.example.demo.repo.MessageRepo;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService{
	@Autowired
	private MessageRepo messageRepo;
	
	@Autowired 
	private UserRepo userRepo;
	
	@Override
	public Message saveMessage(ReciveUserEmailDto receiveId) {
		Message message=new Message();
		message.setSenderId(receiveId.getSenderEmailId());
		message.setReceiverid(receiveId.getReceiverEmailId());
		message.setStatus(false);
		message.setMessage(receiveId.getMessage());
		return messageRepo.save(message);
		
	}

	@Override
	public List<Message> getMessage(String senderId, String receiveId) throws Exception {
		UserEntity senerUser=userRepo.findByEmailId(senderId);
		UserEntity reciverUser=userRepo.findByEmailId(receiveId);
		if(senerUser!=null && reciverUser!=null) {
			return messageRepo.findBySenderIdAndReceiverid(senderId, receiveId);
			
		}
		return new ArrayList<>();
	}

	@Override
	public List<Message> getAllTheMessage() {
		return messageRepo.findAll();
	}

	@Override
	public void readMessage(ReciveUserEmailDto reciveUserEmailDto) {
		List<Message> message=messageRepo.findBySenderIdAndReceiverid(reciveUserEmailDto.getSenderEmailId(), reciveUserEmailDto.getReceiverEmailId());
		for(Message msg:message) {
			msg.setStatus(true);
		}
		messageRepo.saveAll(message);
	}
	

	

}
