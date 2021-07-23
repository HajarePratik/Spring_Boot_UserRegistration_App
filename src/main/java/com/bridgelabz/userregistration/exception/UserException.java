package com.bridgelabz.userregistration.exception;

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
	

	

}
