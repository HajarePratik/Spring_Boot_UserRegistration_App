package com.bridgelabz.userregistration.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bridgelabz.userregistration.dto.LoginDTO;
import com.bridgelabz.userregistration.dto.ResponseDTO;
import com.bridgelabz.userregistration.dto.UserDTO;
import com.bridgelabz.userregistration.model.UserModel;

@Service
public interface IUserService {

	public List<UserModel> getUserData();
	
	public ResponseDTO createUserData(UserDTO userDTO);
	
	public ResponseDTO updateUserDataById(int id,String token,UserDTO userDTO);
	
	public ResponseDTO deleteUserDataById(int id,String token);
	
	public UserModel isUserPresent(String token);

	public ResponseDTO loginUser(LoginDTO loginDTO);

	public ResponseDTO forgetPassword(LoginDTO loginDTO);

	public Boolean verifyEmail(String token);


	
}