package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration

public class OpenAPI {
	
	 @Bean
	    public OpenAPI hrManagementAPI() {
	        return new OpenAPI()
	                .info(new Info()
	                        .title("HR Management System API")
	                        .description("APIs for employees, departments, leaves, attendance and performance reviews")
	                        .version("1.0")
	                        .contact(new Contact()
	                        		            .name("Production Team")
	                        		            .email("prod@gmail.com")));
	    }

	 private OpenAPI info(Info contact) {
		// TODO Auto-generated method stub
		return null;
	 }
	 
	 

	 

}
