package com.bridgelabz.userregistration.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.userregistration.dto.LoginDTO;
import com.bridgelabz.userregistration.dto.ResponseDTO;
import com.bridgelabz.userregistration.dto.UserDTO;
import com.bridgelabz.userregistration.model.UserModel;
import com.bridgelabz.userregistration.service.IUserService;


@RestController
@RequestMapping("/userregistration")

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
		@PutMapping("/update/{token}")
		public ResponseEntity<ResponseDTO> updateUser(@PathVariable String token, @Valid @RequestBody UserDTO userDTO) 
		{
			ResponseDTO userData = userService.updateUserDataById(token, userDTO);
			ResponseDTO respDTO = new ResponseDTO("Updated User with id : " + token, userData);
			return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
		}
		@DeleteMapping("/delete/{token}")
		public ResponseEntity<ResponseDTO> deleteUser(@PathVariable String token)
		{
			userService.deleteUserDataById(token);
			ResponseDTO respDTO = new ResponseDTO("Delete User with id :",token);
			return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);
		}
		@GetMapping("/verifyemail/{token}")
		public ResponseEntity<ResponseDTO> verifyemail(@PathVariable("token") String token)
		{
			return new ResponseEntity<ResponseDTO>
			(
					new ResponseDTO("email is verified",userService.verify(token),201,"true"),HttpStatus.ACCEPTED
			);
		}
		
		@PostMapping("/login/{token}")
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
		