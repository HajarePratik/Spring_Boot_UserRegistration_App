package com.bridgelabz.userregistration.exception;

import org.springframework.http.HttpStatus;

public class UserException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserException(String message)
	{
		super(message);
	}
	public UserException(int statusCode, String statusmessage)
	{
		super(statusmessage);
	}
	public UserException(String string, HttpStatus ok, Object object, String string2) {
		// TODO Auto-generated constructor stub
	}
	

	

}
