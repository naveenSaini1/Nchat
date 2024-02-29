package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.model.Friend;

public interface FriendService {
	public Map<String, Object> saveFriends(String currentuserId, String friendEmail) throws Exception;
	public List<Friend> getAllFriends(String currentUserId) throws Exception;
	

}
