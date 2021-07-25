package com.bridgelabz.userregistration.dto;


import com.bridgelabz.userregistration.model.UserModel;

import lombok.Data;

public @Data class ResponseDTO {

	
	private String message;
	private Object data;
	
	public ResponseDTO(String message, Object data) {
		this.message = message;
		this.data = data;
	}

	public ResponseDTO(String string, UserModel verify, int i, String string2) {
		// TODO Auto-generated constructor stub
	}

}
