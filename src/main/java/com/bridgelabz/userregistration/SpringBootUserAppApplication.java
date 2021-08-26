package com.bridgelabz.userregistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.bridgelabz.userregistration")
@EnableJpaRepositories("com.bridgelabz.userregistration.respository")
@EnableEurekaClient
public class SpringBootUserAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootUserAppApplication.class, args);
	}

}
