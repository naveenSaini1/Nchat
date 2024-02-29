package com.example.demo.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.UserDto;
import com.example.demo.model.UserEntity;

public interface UserService {
	
	public UserEntity getUserByEmail(String userEmail) throws Exception;
	public UserEntity saveUser(UserDto userDto) throws IOException;
	public UserEntity loginUser(String email,String password) throws Exception;
	public UserEntity switchActiveUser(UserEntity userEntity);

	

}
