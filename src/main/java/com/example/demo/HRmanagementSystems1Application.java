package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HRmanagementSystems1Application {

	public static void main(String[] args) {
		SpringApplication.run(HRmanagementSystems1Application.class, args);
	}

}
