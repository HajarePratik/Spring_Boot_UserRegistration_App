package com.bridgelabz.userregistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.bridgelabz.userregistration.respository")
public class SpringBootUserAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootUserAppApplication.class, args);
	}

}
