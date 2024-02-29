package com.example.demo.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.FriendsDto;
import com.example.demo.model.Friend;
import com.example.demo.model.Message;
import com.example.demo.model.UserEntity;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.CommonService;
import com.example.demo.service.MessageService;
import com.example.demo.service.UtilityService;

@Service
public class CommonServiceImpl implements CommonService {
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private UtilityService utitlityService;
	
	@Autowired
	private MessageService messageService;
	


	@Override
	public Map<String, Object> getTheDataOfTheUser(String userId) throws Exception {
		HashMap<String, Object> object	=new HashMap<>();
		List<Friend> friends			=null;
		UserEntity user=userRepo.findById(userId).get();
		if(user!=null) {
			
		friends	= utitlityService.getAllFriends(userId);
		object.put("user", user);
		object.put("friends", convertTheFriendsToFriendsDtoMessage(friends,user));
		return object;			
		}
		throw new Exception("user Not found");
	}

	public List<FriendsDto> convertTheFriendsToFriendsDtoMessage(List<Friend> friends,UserEntity user) throws Exception{
		List<FriendsDto> friendsDtoList=new ArrayList<>();
		List<Message> allDataBaseMessages=messageService.getAllTheMessage();

		for(Friend frnd:friends) {
			UserEntity dataBaseFriend=userRepo.findById(frnd.getCurrentFriendUserId()).get();
			
			
			List<Message> messageList=getUserMessageSpecifyWhichFriendHave(allDataBaseMessages,user.getEmailId(),dataBaseFriend.getEmailId());
			FriendsDto friendsDto=new FriendsDto();
			friendsDto.setName(dataBaseFriend.getName());
			friendsDto.setBio(dataBaseFriend.getBio());
			friendsDto.setEmailId(dataBaseFriend.getEmailId());
			friendsDto.setImage(dataBaseFriend.getImage());
			friendsDto.setActive(dataBaseFriend.isActive());
			friendsDto.setMessages(messageList);
			friendsDtoList.add(friendsDto);
		}
		
		return friendsDtoList;
		
	}
	
	public List<Message> getUserMessageSpecifyWhichFriendHave(List<Message> requestMessagesList,String senderEmailId,String receiverEmailId){
		List<Message>  responseMessageList		=		new ArrayList<>();
		for(Message itrateMessage:requestMessagesList) {
			if((itrateMessage.getSenderId().equals(senderEmailId )&& itrateMessage.getReceiverid().equals(receiverEmailId)) 
					|| (itrateMessage.getSenderId().equals(receiverEmailId)  && itrateMessage.getReceiverid().equals(senderEmailId))){
				responseMessageList.add(itrateMessage);
				}
		}
		
		
		return responseMessageList;
	}
}
