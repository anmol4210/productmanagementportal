package com.nagarro.productmanagement;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;



@SpringBootApplication
@ComponentScan("com.nagarro.productmanagement")
@EnableScheduling
public class ProductManagementApplication  {

	
	public static void main(String[] args) {
		SpringApplication.run(ProductManagementApplication.class, args);
	}
}
