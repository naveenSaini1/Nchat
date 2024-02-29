package com.example.demo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.ReciveUserEmailDto;
import com.example.demo.dto.UserDto;
import com.example.demo.impl.CommonServiceImpl;
import com.example.demo.impl.FriendServiceImpl;
import com.example.demo.impl.UserServiceImpl;
import com.example.demo.model.UserEntity;
import com.example.demo.service.CommonService;
import com.example.demo.service.FriendService;
import com.example.demo.service.MessageService;
import com.example.demo.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	 private  SimpMessagingTemplate messagingTemplate;

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FriendService friendService;
	
	@Autowired
	private MessageService messageService;

	@GetMapping("/userFriends/{userId}")
	public Map<String,Object> getTheAllFreidnsOfUserByUserId(@PathVariable("userId") String userId) throws Exception{
		return commonService.getTheDataOfTheUser(userId);
		
	}
	@PostMapping("/register")
	public UserEntity registerTheUser(@RequestParam("name") String name,@RequestParam("file") MultipartFile file,
			@RequestParam("emailId") String emailId,@RequestParam("bio") String bio,@RequestParam String password) throws IOException {
		System.out.println("========"+name+"======="+file.getOriginalFilename());
		UserDto userDto=new UserDto();
		userDto.setBio(bio);
		userDto.setEmailId(emailId);
		userDto.setFile(file);
		userDto.setName(name);
		userDto.setPassword(password);

		return  userService.saveUser(userDto);
	}
	
	@PostMapping("/login/{email}/{password}")
	public UserEntity loginUser(@PathVariable("email") String email,@PathVariable String password) throws Exception {
		return userService.loginUser(email, password);
	}
	
	@GetMapping("/addFriend/{userId}/{emailId}")
	public Map<String,Object> addFriendToThe(@PathVariable("userId") String userId,@PathVariable("emailId") String emailId) throws Exception{
		System.out.println(userId+"    --- "+emailId);
		return friendService.saveFriends(userId, emailId);
	}
	
	@PostMapping("/user/reedMessage")
	public String saveSeenMessage(@RequestBody ReciveUserEmailDto receiveMessageEmailDto) throws Exception {
		System.out.println("hello");
		messageService.readMessage(receiveMessageEmailDto);
		Map<String, String> map=new HashMap<>();
		map.put("emailId", receiveMessageEmailDto.getReceiverEmailId());
		UserEntity user= userService.getUserByEmail(receiveMessageEmailDto.getSenderEmailId());
		System.out.println(receiveMessageEmailDto);
		messagingTemplate.convertAndSend("/topic/seen/"+user.getUserId(),map);
		
		return "working fine";
	}
}
