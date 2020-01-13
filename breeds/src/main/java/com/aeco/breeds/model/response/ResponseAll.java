package com.aeco.breeds.model.response;

import java.util.List;

public class ResponseAll {
	private List<Message> message;
	private String status;

	public List<Message> getMessage() {
		return message;
	}
	public void setMessage(List<Message> message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
