package com.bridgelabz.userregistration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.userregistration.dto.ResponseDTO;
import com.bridgelabz.userregistration.dto.UserRegistrationDTO;
import com.bridgelabz.userregistration.model.Model_UserRegistration;
import com.bridgelabz.userregistration.service.IUserRegistrationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/userregistration")
@Slf4j
public class UserRegistrationController {

		@Autowired(required = true)
		private IUserRegistrationService userRegistrationService;
		
		@RequestMapping(value = {"","/","/get"})
		public ResponseEntity<ResponseDTO> getUserData()
		{
			List<Model_UserRegistration> userDataList = userRegistrationService.getUserData(); 
			ResponseDTO resDTO = new ResponseDTO("Get Call Successful", userDataList);
			return new ResponseEntity<ResponseDTO>(resDTO, HttpStatus.OK);
		}
		@GetMapping("/get/{userId}")
		public ResponseEntity<ResponseDTO> getUserData(@PathVariable("userId") int userId)
		{
			Model_UserRegistration userData = userRegistrationService.getUserDataById(userId);
			ResponseDTO resDTO = new ResponseDTO("Get Call For ID Successful", userData);
			return new ResponseEntity<ResponseDTO>(resDTO,HttpStatus.OK);
		}
		@PostMapping("/create")
		public ResponseEntity<ResponseDTO> createUserData(@RequestBody UserRegistrationDTO userRegistrationDTO)
		{
			log.debug("User DTO :"+userRegistrationDTO.toString());
			Model_UserRegistration userData = userRegistrationService.createUserData(userRegistrationDTO);
			ResponseDTO resDTO = new ResponseDTO("Create User Details Sucessfully :", userData);
			return new ResponseEntity<ResponseDTO>(resDTO,HttpStatus.OK);
		}
		@PutMapping("/update/{userId}")
		public ResponseEntity<ResponseDTO> updateUserData(@PathVariable("userId") int userId,@RequestBody UserRegistrationDTO userRegistrationDTO)
		{
			
			Model_UserRegistration userData = userRegistrationService.updateUserData(userId,userRegistrationDTO);;
			ResponseDTO resDTO = new ResponseDTO("Updated User Details Sucessfully :", userData);
			return new ResponseEntity<ResponseDTO>(resDTO,HttpStatus.OK);
		}
		@DeleteMapping("/delete/{userId}")
		public ResponseEntity<ResponseDTO> deleteUserData(@PathVariable("userId") int userId)
		{
			userRegistrationService.deleteUserData(userId);
			ResponseDTO resDTO = new ResponseDTO("Delete Sucessfully","Deleted Id: " +userId);
			return new ResponseEntity<ResponseDTO>(resDTO,HttpStatus.OK);
		}
}
		