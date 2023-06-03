package com.example.TechWorld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
/*@SpringBootApplication*/
public class TechWorldApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechWorldApplication.class, args);
	}

}
