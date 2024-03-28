package com.obms.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan("com.obms")
@EnableJpaRepositories(basePackages = {"com.obms.repo"})
@EnableTransactionManagement
@EntityScan(basePackages = {"com.obms"})
public class OnlineBookManagementApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(OnlineBookManagementApplication.class, args);
	}
}

