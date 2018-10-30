package com.nagarro.productmanagement;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.nagarro.productmanagement")
public class ProductManagementApplication  {

	
	public static void main(String[] args) {
		SpringApplication.run(ProductManagementApplication.class, args);
	}
}
