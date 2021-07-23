package com.bridgelabz.userregistration.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bridgelabz.userregistration.dto.ResponseDTO;
import com.bridgelabz.userregistration.dto.UserDTO;
import com.bridgelabz.userregistration.model.UserModel;

@Service
public interface IUserService {

	public List<UserModel> getUserData();
	
	public ResponseDTO createUserData(UserDTO userDTO);
	
	public ResponseDTO updateUserDataById(String id,UserDTO userDTO);
	
	public ResponseDTO deleteUserDataById(String id);


	
}