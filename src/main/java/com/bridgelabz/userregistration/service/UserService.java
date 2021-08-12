package com.bridgelabz.userregistration.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bridgelabz.userregistration.dto.LoginDTO;
import com.bridgelabz.userregistration.dto.ResponseDTO;
import com.bridgelabz.userregistration.dto.UserDTO;
import com.bridgelabz.userregistration.exception.UserException;
import com.bridgelabz.userregistration.model.UserModel;
import com.bridgelabz.userregistration.respository.UserRespository;
import com.bridgelabz.userregistration.util.JMSUtil;
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
			String body = "http://localhost:8080/verifyemail/" + token;
			System.out.println(body);
			JMSUtil.sendEmail(createUser.getEmail(), "verification email for user " + createUser.getFirstName(), body);
			return new ResponseDTO("User Successfully Added",createUser);
	}

	@Override
	public ResponseDTO updateUserDataById(int id,String token, @Valid UserDTO userDTO)
	{
		
		int tokenid = TokenUtil.decodeToken(token);
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
	public ResponseDTO deleteUserDataById(int id,String token)
	{
		
		int tokenid = TokenUtil.decodeToken(token);
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

	@Override
	public UserModel verify(String token) 
	{
		int tokenid = TokenUtil.decodeToken(token);
		UserModel verifyUser = userRespository.findById(tokenid).orElseThrow(()-> new UserException(400,"User not Exist"));
		verifyUser.setVerify_Boolean(true);
		userRespository.save(verifyUser);
		return verifyUser;
	}
	
	@Override
	public ResponseDTO loginUser(LoginDTO loginDTO)
	{
		Optional<UserModel> isUserPresent = userRespository.findByEmail(loginDTO.getEmail());
		boolean email = isUserPresent.get().getEmail().matches(loginDTO.getEmail());
		boolean password = isUserPresent.get().getPassword().contains(loginDTO.getPassword());
		
		if(email == false && password == false)
		{
			throw new UserException("Authentication Failed",HttpStatus.OK,null,"false");
		}
		else
		{
			String registerToken = TokenUtil.createToken(isUserPresent.get().getUserId());
			return new ResponseDTO("Login Succesful","Registration Token :"+registerToken);
		}
		
	}
	
	@Override
	public ResponseDTO forgetPassword(LoginDTO forgotDTO)
	{
	
		Optional<UserModel> isUserPresent = userRespository.findByEmail(forgotDTO.getEmail());
		String body = "http://localhost:8080/resetpassword/" + TokenUtil.createToken(isUserPresent.get().getUserId());
		JMSUtil.sendEmail(isUserPresent.get().getEmail(), "Reset Password", body);
		return new ResponseDTO("Reset Password", body);
	}
	
	
	
}
