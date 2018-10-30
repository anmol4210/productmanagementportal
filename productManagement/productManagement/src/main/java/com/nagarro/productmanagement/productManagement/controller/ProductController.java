package com.nagarro.productmanagement.productManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.productmanagement.productManagement.dto.NewProductDto;
import com.nagarro.productmanagement.productManagement.dto.Response;
import com.nagarro.productmanagement.productManagement.dto.SellerRegistrationDto;
import com.nagarro.productmanagement.productManagement.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;
	
	@GetMapping("/products/{id}")
	public Response getProducts(@PathVariable String id) {
		System.out.println("id:"+id);
		 return productService.getProducts(id);		
	}
	
	@PostMapping("/products")
	public Response addProduct(@RequestBody NewProductDto product) {
		
		 return productService.addProduct(product);		
	}
}
