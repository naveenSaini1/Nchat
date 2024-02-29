package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.example.demo.dto.ReciveUserEmailDto;
import com.example.demo.model.Message;
import com.example.demo.model.UserEntity;
import com.example.demo.service.MessageService;
import com.example.demo.service.UserService;

@RestController
public class ChatController {
	@Autowired
    private  SimpMessageSendingOperations simpMessageSendingOperations;
	
	 private final SimpMessagingTemplate messagingTemplate;
	 @Autowired
	 private UserService userService;

	 @Autowired
	 private MessageService messageService;
	    public ChatController(SimpMessagingTemplate messagingTemplate) {
	        this.messagingTemplate = messagingTemplate;
	    }
	
	// sendint message privetly
	@MessageMapping("/userMessage")
	public void SendMessageToTheUser(@Payload ReciveUserEmailDto reciveUserPayload) throws Exception {
		
		UserEntity user= userService.getUserByEmail(reciveUserPayload.getReceiverEmailId());
		UserEntity user1= userService.getUserByEmail(reciveUserPayload.getSenderEmailId());
		Message message=	messageService.saveMessage(reciveUserPayload);
		System.out.println(reciveUserPayload);		
        messagingTemplate.convertAndSend("/topic/"+user.getUserId(), message);
       String receiverId=message.getReceiverid();
        message.setSenderId(receiverId);
        messagingTemplate.convertAndSend("/topic/"+user1.getUserId(), message);

       

		
		
	}
	@MessageMapping("/activeUser")
	@SendTo("/topic/active")
	public UserEntity activeUserMapping(@Payload UserEntity userEntity,SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("emailId", userEntity.getEmailId());
		return userService.switchActiveUser(userEntity);
	}
	
	
	  @EventListener
	    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) throws Exception {
		  System.out.println("someone is disconnected");
	        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
	        String emailId = (String) headerAccessor.getSessionAttributes().get("emailId");
	        if (emailId != null) {
	            
	           UserEntity userEntity= userService.getUserByEmail(emailId);
	            messagingTemplate.convertAndSend("/topic/active",  userService.switchActiveUser(userEntity));
	        }
	    }

}
