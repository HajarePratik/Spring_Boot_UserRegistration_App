package com.bridgelabz.userregistration.respository;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.bridgelabz.userregistration.model.UserModel;
@Repository
public interface UserRespository extends JpaRepository<UserModel, Integer> {

	//public Optional<UserModel> findByemail(String string); 
	
}
