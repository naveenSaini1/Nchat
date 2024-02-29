package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.UserEntity;


public interface UserRepo extends JpaRepository<UserEntity, String> {
	UserEntity findByEmailId(String emailId);
	UserEntity findByEmailIdAndPassword(String email,String password);

}
