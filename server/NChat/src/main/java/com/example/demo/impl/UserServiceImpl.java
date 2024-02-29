package com.example.demo.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.UserDto;
import com.example.demo.model.UserEntity;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl  implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserEntity getUserByEmail(String userEmail) throws Exception {
		UserEntity user=userRepo.findByEmailId(userEmail);
		if(user!=null) {
			return user;
		}
		throw new Exception("email not found");
		
	}

	@Override
	public UserEntity saveUser(UserDto userDto) throws IOException {
		UserEntity createdUser=null;
		String userId=generateKey();
		UserEntity newUser=new UserEntity();
		newUser.setUserId(userId);
		newUser.setName(userDto.getName());
		newUser.setBio(userDto.getBio());
		newUser.setEmailId(userDto.getEmailId());
		newUser.setPassword(userDto.getPassword());
		newUser.setActive(false);
		System.out.println(userDto);
		if(userDto.getFile()!=null) {
		newUser.setImage(userDto.getFile().getOriginalFilename());
		
		String targetPath=new File("/home/naveen/Documents/ChatApplications/Whatsapp2.O/static").getAbsolutePath();	
		System.out.println("target "+targetPath+"ll"+File.separator);
		Files.copy(userDto.getFile().getInputStream(),Paths.get(targetPath+"/img/"+File.separator+userDto.getFile().getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
		}
	   createdUser= userRepo.save(newUser);
		
		return createdUser;

	}

	@Override
	public UserEntity loginUser(String email, String password) throws Exception {
		UserEntity user= userRepo.findByEmailIdAndPassword(email, password);
	  if(user!=null) {
		  
		  return user;
	  }
	  throw new Exception("Email or password Not found");
		
	}
	
	@Override
	public UserEntity switchActiveUser(UserEntity userEntity) {
	 UserEntity dataBaseuserEntity=	userRepo.findById(userEntity.getUserId()).get();
	 if(dataBaseuserEntity!=null) {
		 dataBaseuserEntity.setActive(!dataBaseuserEntity.isActive());
		UserEntity savedUserEntity= userRepo.save(dataBaseuserEntity);
		savedUserEntity.setUserId(null);
		savedUserEntity.setPassword(null);
		return savedUserEntity;
	 }
		return null;
	}
	
	 	
	public static String generateKey() {
        return UUID.randomUUID().toString();
    }
	
	
  

}
