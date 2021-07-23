package com.bridgelabz.userregistration.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;

import com.bridgelabz.userregistration.dto.UserDTO;

import lombok.Data;

@Entity
@Table(name = "user_details")
public @Data class UserModel 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int userId;
	
	public String firstName;
	public String lastName;
	public LocalDate dob;
	public String email;
	public String password;
	public LocalDate registerDate;
	public LocalDate updateDate;
	public Boolean verify_Boolean;
	
	public UserModel() {

	}
	
	public UserModel(int userId, @Valid UserDTO userDTO)
	{
		// TODO Auto-generated constructor stub
		this.firstName = userDTO.firstName;
		this.lastName = userDTO.lastName;
		this.dob = userDTO.dob;
		this.email = userDTO.email;
		this.password = userDTO.password;
	}
	
	public UserModel(String email,@Valid UserDTO userDTO)
	{
		// TODO Auto-generated constructor stub
	}
	

	
}
