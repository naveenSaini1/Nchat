package com.example.demo.dto;

import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public class UserDto {

	private String name;
	private String emailId;
	
	private String image;
	
	private String bio;
	private MultipartFile file;
	
	private String password;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	@Override
	public String toString() {
		return "UserDto [name=" + name + ", emailId=" + emailId + ", image=" + image + ", bio=" + bio + ", file=" + file
				+ ", password=" + password + "]";
	}
	
	
}
