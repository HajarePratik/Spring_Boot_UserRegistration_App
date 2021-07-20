package com.bridgelabz.userregistration.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.ToString;

public @ToString class UserRegistrationDTO 
{

	@Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$",message = "User First Name cannot be Correct")
	public String Firstname;
	
	@Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$",message = "User Last Name cannot be Correct")
	public String Lastname;

	
	@NotEmpty(message = "Date of Birth should Not be Empty")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	public LocalDate DOB;
	
	@Pattern(regexp = "^[a-zA-Z0-9]+([+_.-][a-zA-Z0-9]+)*[@][a-zA-Z0-9]+[.][a-zA-Z]{2,4}([.][a-zA-Z]{2,4})?",message = "Email ID cannot be Correct")
	public String Email;
	
	
	public String Password;
	
	
	
	
	
	
}
