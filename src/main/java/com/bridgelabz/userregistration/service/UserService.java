package com.bridgelabz.userregistration.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bridgelabz.userregistration.dto.ResponseDTO;
import com.bridgelabz.userregistration.dto.UserDTO;
import com.bridgelabz.userregistration.exception.UserException;
import com.bridgelabz.userregistration.model.UserModel;
import com.bridgelabz.userregistration.respository.UserRespository;
import com.bridgelabz.userregistration.util.TokenUtil;

@Service
public class UserService implements IUserService
{

	@Autowired
	private UserRespository userRespository;
	
	@Autowired
	ModelMapper modelmapper;

	@Override
	public List<UserModel> getUserData() 
	{
		return userRespository.findAll();
	}

	@Override
	public ResponseDTO createUserData(UserDTO userDTO) 
	{
		
			UserModel createUser = modelmapper.map(userDTO, UserModel.class);
			createUser.setRegisterDate(LocalDate.now());
			userRespository.save(createUser);
			String token;
			token = TokenUtil.createToken(createUser.getUserId());
			System.out.println("Token:"+token);
			return new ResponseDTO("User Successfully Added",createUser);
	}

	@Override
	public ResponseDTO updateUserDataById(String id, @Valid UserDTO userDTO)
	{
		
		int tokenid = TokenUtil.decodeToken(id);
		Optional<UserModel> isUserPresent = userRespository.findById(tokenid);
		if(isUserPresent.isPresent()) 
		{
			isUserPresent.get().setFirstName(userDTO.getFirstName());
			isUserPresent.get().setLastName(userDTO.getLastName());
			isUserPresent.get().setDob(userDTO.getDob());
			isUserPresent.get().setEmail(userDTO.getEmail());
			isUserPresent.get().setPassword(userDTO.getPassword());
			isUserPresent.get().setUpdateDate(LocalDate.now());
			userRespository.save(isUserPresent.get());
			return new ResponseDTO("User Data Succefully Updated", isUserPresent.get());
		}
		else
		{
			throw new UserException(400,"User Details Not found");
		}
	}

	@Override
	public ResponseDTO deleteUserDataById(String id) {
		
		int tokenid = TokenUtil.decodeToken(id);
		Optional<UserModel> isUserPresent = userRespository.findById(tokenid);
		if(isUserPresent.isPresent())
		{
			userRespository.deleteById(tokenid);
			return new ResponseDTO("Deleted Successfully", HttpStatus.ACCEPTED);
		}
		else
		{
			throw new UserException(400,"User Details Not found");
		}
	}

	
	
}
