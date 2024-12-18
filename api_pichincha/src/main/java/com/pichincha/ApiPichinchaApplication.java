package com.pichincha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class ApiPichinchaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPichinchaApplication.class, args);
	}

}
