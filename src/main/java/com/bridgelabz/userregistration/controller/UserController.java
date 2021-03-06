package com.bridgelabz.userregistration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.userregistration.dto.LoginDTO;
import com.bridgelabz.userregistration.dto.ResponseDTO;
import com.bridgelabz.userregistration.dto.UserDTO;
import com.bridgelabz.userregistration.model.UserModel;
import com.bridgelabz.userregistration.service.IUserService;

@RestController
public class UserController {

		@Autowired(required = true)
		private IUserService userService;
		
		@GetMapping("/get")
		public ResponseEntity<ResponseDTO> getUserData()
		{
			List<UserModel> userDataList = userService.getUserData(); 
			ResponseDTO resDTO = new ResponseDTO("Get Call Successful", userDataList);
			return new ResponseEntity<ResponseDTO>(resDTO, HttpStatus.OK);
		}
		@PostMapping("/create")
		public ResponseEntity<ResponseDTO> createUserData(@RequestBody UserDTO userDTO)
		{
			ResponseDTO userData = userService.createUserData(userDTO);
			ResponseDTO resDTO = new ResponseDTO("Create User Details Sucessfully :"+userData, userDTO);
			return new ResponseEntity<ResponseDTO>(resDTO,HttpStatus.OK);
		}
		@PutMapping("/update/{token}/{id}")
		public ResponseEntity<ResponseDTO> updateUser(int id,@PathVariable String token,@RequestBody UserDTO userDTO) 
		{
			ResponseDTO userData = userService.updateUserDataById(id,token, userDTO);
			ResponseDTO respDTO = new ResponseDTO("Updated User with id : " + token, userData);
			return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
		}
		@DeleteMapping("/delete/{token}/{id}")
		public ResponseEntity<ResponseDTO> deleteUser(int id,@PathVariable String token)
		{
			userService.deleteUserDataById(id,token);
			ResponseDTO respDTO = new ResponseDTO("Delete User with id :",token);
			return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);
		}
		@GetMapping("/isuserpresent/{token}")
		public ResponseEntity<ResponseDTO> isUserPresent(@PathVariable("token") String token)
		{
			return new ResponseEntity<ResponseDTO>
			(
					new ResponseDTO("User is present",userService.isUserPresent(token)),HttpStatus.OK
			);
		}
		
		@GetMapping("/verifyemail/{token}")
		public Boolean verifyEmail(@PathVariable("token") String token)
		{
			return userService.verifyEmail(token);
		}
		@PostMapping("/login")
		public ResponseEntity<ResponseDTO> loginUser(@RequestBody LoginDTO loginDTO)
		{
			ResponseDTO respDTO = userService.loginUser(loginDTO);
			return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
		}
		
		@PostMapping("/forgotpassword")
		public ResponseEntity<ResponseDTO> forgotPassword(@RequestBody LoginDTO forgotDTO, BindingResult result) {
			ResponseDTO respDTO = userService.forgetPassword(forgotDTO);
			return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
		}
}
		