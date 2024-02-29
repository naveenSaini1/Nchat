package com.example.demo.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Friend;
import com.example.demo.model.UserEntity;
import com.example.demo.repo.FriendRepo;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.CommonService;
import com.example.demo.service.FriendService;

@Service
public class FriendServiceImpl implements FriendService {
	@Autowired
	private FriendRepo friendRepo;
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CommonService commonService;

	@Override
	public Map<String,Object> saveFriends(String currentuserId, String friendEmail) throws Exception {
		
		UserEntity currentUser=userRepo.findById(currentuserId).get();
		UserEntity currentFriendUser=userRepo.findByEmailId(friendEmail);
		if(currentUser!=null && currentFriendUser!=null) {
			
			Friend friend=new Friend();
			friend.setCurrentUserId(currentuserId);
			friend.setCurrentFriendUserId(currentFriendUser.getUserId());
			  friendRepo.save(friend);
			  
			  return commonService.getTheDataOfTheUser(currentuserId);
			 
		}
		
		throw new Exception("email not found");
		
		
	}

	@Override
	public List<Friend> getAllFriends(String currentUserId) throws Exception {
		
		UserEntity currentUser=userRepo.findById(currentUserId).get();
		if(currentUser!=null) {
			return friendRepo.findByCurrentUserId(currentUserId);
			
		}
		throw new Exception("Id not found");
	}
	
	
	

}
