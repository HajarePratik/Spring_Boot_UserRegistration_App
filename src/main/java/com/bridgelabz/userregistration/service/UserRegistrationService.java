package com.bridgelabz.userregistration.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgelabz.userregistration.dto.UserRegistrationDTO;
import com.bridgelabz.userregistration.exception.UserRegistrationException;
import com.bridgelabz.userregistration.model.Model_UserRegistration;
import com.bridgelabz.userregistration.respository.UserRegistrationRespository;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class UserRegistrationService implements IUserRegistrationService
{

	@Autowired
	private UserRegistrationRespository userregistrationRespository;

	@Override
	public List<Model_UserRegistration> getUserData() 
	{
		
		return userregistrationRespository.findAll();
	}

	@Override
	public Model_UserRegistration getUserDataById(int userId) 
	{
		
		return userregistrationRespository.findById(userId)
				.orElseThrow(()-> new UserRegistrationException("User with UserId " + userId + " does not exists...!"));
	}

	@Override
	public Model_UserRegistration createUserData(UserRegistrationDTO userRegistrationDTO) 
	{
		
		Model_UserRegistration userData = new Model_UserRegistration(userRegistrationDTO);
		BeanUtils.copyProperties(userRegistrationDTO,userData);
		log.debug("User Data:"+userData.toString());
		return userregistrationRespository.save(userData);
	}

	@Override
	public Model_UserRegistration updateUserData(int userId, UserRegistrationDTO userRegistrationDTO) 
	{
		Model_UserRegistration userData = this.getUserDataById(userId);
		userData.updateUserData(userRegistrationDTO);
		return userregistrationRespository.save(userData);
	}

	@Override
	public void deleteUserData(int userId) 
	{
		Model_UserRegistration userData = this.getUserDataById(userId);
		userregistrationRespository.delete(userData);
		
		
	}
	
	
	
}
