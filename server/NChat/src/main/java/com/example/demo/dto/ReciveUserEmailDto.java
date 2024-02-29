package com.example.demo.dto;

public class ReciveUserEmailDto {
	private String senderEmailId;
	private String receiverEmailId;
	private String message;
	public String getSenderEmailId() {
		return senderEmailId;
	}
	public void setSenderEmailId(String senderEmailId) {
		this.senderEmailId = senderEmailId;
	}
	public String getReceiverEmailId() {
		return receiverEmailId;
	}
	public void setReceiverEmailId(String receiverEmailId) {
		this.receiverEmailId = receiverEmailId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "ReciveUserEmailDto [senderEmailId=" + senderEmailId + ", receiverEmailId=" + receiverEmailId
				+ ", message=" + message + "]";
	}
	
	

	

	
	
}

