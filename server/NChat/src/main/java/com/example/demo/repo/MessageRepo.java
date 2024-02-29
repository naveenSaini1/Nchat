package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Message;

public interface MessageRepo  extends JpaRepository<Message, Integer>{

	List<Message> findBySenderIdAndReceiverid(String senderId, String receiverid);
	
}
