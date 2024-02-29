package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Friend;
import com.example.demo.model.UserEntity;

public interface UtilityService {
	
	public List<Friend> getAllFriends(String currentUserId) throws Exception;


}
