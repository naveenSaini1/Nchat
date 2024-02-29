package com.example.demo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Friend;
import com.example.demo.model.UserEntity;
import com.example.demo.repo.FriendRepo;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.UtilityService;

@Service
public class UtilityServiceImpl  implements UtilityService{

	@Autowired
	private FriendRepo friendRepo;
	@Autowired
	private UserRepo userRepo;
	
	
	@Override
	public List<Friend> getAllFriends(String currentUserId) throws Exception {
		UserEntity currentUser=userRepo.findById(currentUserId).get();
		if(currentUser!=null) {
			return friendRepo.findByCurrentUserId(currentUserId);
			
		}
		throw new Exception("Id not found");
	}


	

}
