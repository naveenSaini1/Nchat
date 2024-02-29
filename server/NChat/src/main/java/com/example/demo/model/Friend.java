package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Friend {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer friendId;
	private String currentUserId;
	private String currentFriendUserId;
	public Integer getFriendId() {
		return friendId;
	}
	public void setFriendId(Integer friendId) {
		this.friendId = friendId;
	}
	public String getCurrentUserId() {
		return currentUserId;
	}
	public void setCurrentUserId(String currentUserId) {
		this.currentUserId = currentUserId;
	}
	public String getCurrentFriendUserId() {
		return currentFriendUserId;
	}
	public void setCurrentFriendUserId(String currentFriendUserId) {
		this.currentFriendUserId = currentFriendUserId;
	}
	
	
	

}
