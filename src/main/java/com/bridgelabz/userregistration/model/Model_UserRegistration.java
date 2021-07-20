package com.bridgelabz.userregistration.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bridgelabz.userregistration.dto.UserRegistrationDTO;

import lombok.Data;

@Entity
@Table(name = "user_details")
public @Data class Model_UserRegistration {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int user_Id;
	
	@Column(name = "name")
	public String Firstname;
	public String Lastname;
	public LocalDate DOB;
	public String Email;
	public String Password;
	public LocalDate RegisterDate;
	public LocalDate UpdateDate;
	public Boolean Verify_Boolean;
	private String profilePic;
	
	public Model_UserRegistration() {

	}
	
	public Model_UserRegistration(UserRegistrationDTO userRegistrationDTO) {
		// TODO Auto-generated constructor stub
		this.Firstname = userRegistrationDTO.Firstname;
		this.Lastname = userRegistrationDTO.Lastname;
		this.DOB = userRegistrationDTO.DOB;
		this.Email = userRegistrationDTO.Email;
		this.Password = userRegistrationDTO.Password;
		
	}
	
	public void updateUserData(UserRegistrationDTO userRegistrationDTO) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
