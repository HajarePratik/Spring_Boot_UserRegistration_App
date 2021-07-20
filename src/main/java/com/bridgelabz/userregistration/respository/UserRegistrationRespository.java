package com.bridgelabz.userregistration.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.userregistration.model.Model_UserRegistration;

@Repository
public interface UserRegistrationRespository extends JpaRepository<Model_UserRegistration, Integer> {

}
