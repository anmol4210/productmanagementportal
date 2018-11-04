package com.nagarro.productmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.productmanagement.dto.Response;
import com.nagarro.productmanagement.service.CategoryService;


@RestController
@CrossOrigin
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/categories")
	public Response getAllCategories() {
		return categoryService.getAllCategories();
	}
	@GetMapping("/categories/{productid}")
	public Response getproductCategories(@PathVariable String productid) {
		return categoryService.getProductCategories(productid);
	}

}
