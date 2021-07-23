package com.bridgelabz.userregistration.dto;

import java.time.LocalDate;

import com.sun.istack.NotNull;

import lombok.Data;

public @Data class UserDTO 
{
	
	@NotNull
	public String firstName;
	
	@NotNull
	public String lastName;

	@NotNull
	public LocalDate dob;
	
	@NotNull
	public String email;
	
	@NotNull
	public String password;


	
}
