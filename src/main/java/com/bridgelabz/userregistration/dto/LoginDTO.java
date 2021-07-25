package com.bridgelabz.userregistration.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LoginDTO {

	@NotBlank
	@Email
	public String email;
	
	@Size(min=4,max=16)
	private String Password;
	
}
