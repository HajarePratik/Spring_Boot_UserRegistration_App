package com.bridgelabz.userregistration.service;

import java.util.List;

import com.bridgelabz.userregistration.dto.UserRegistrationDTO;
import com.bridgelabz.userregistration.model.Model_UserRegistration;



public interface IUserRegistrationService {

	public List<Model_UserRegistration> getUserData();
	
	public Model_UserRegistration getUserDataById(int userId);
	
	public Model_UserRegistration createUserData(UserRegistrationDTO userRegistrationDTO);
	
	public Model_UserRegistration updateUserData(int userId,UserRegistrationDTO userRegistrationDTO);
	
	public void deleteUserData(int userId);
}
