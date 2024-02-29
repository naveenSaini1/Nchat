package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Friend;

public interface FriendRepo  extends JpaRepository<Friend, Integer>{
	
	List<Friend> findByCurrentUserId(String currentUserId);

}
