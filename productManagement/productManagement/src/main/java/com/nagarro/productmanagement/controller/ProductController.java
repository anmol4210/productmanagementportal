package com.nagarro.productmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.productmanagement.dto.NewProductDto;
import com.nagarro.productmanagement.dto.ProductDto;
import com.nagarro.productmanagement.dto.Response;
import com.nagarro.productmanagement.dto.ResponseDto;
import com.nagarro.productmanagement.dto.SellerRegistrationDto;
import com.nagarro.productmanagement.dto.StatusDto;
import com.nagarro.productmanagement.service.ProductService;

@RestController
@CrossOrigin
public class ProductController {

	@Autowired
	ProductService productService;
	
	@GetMapping("/products/{id}")
	public Response getProducts(@PathVariable String id) {
		
		 return productService.getProducts(id);		
	}
	
	@GetMapping("/product/{id}")
	public Response getProduct(@PathVariable String id) {
		
		 return productService.getProduct(id);		
	}
	
	@PostMapping("/products")
	public Response addProduct(@RequestBody NewProductDto product) {
		System.out.println("add product controller.......");
		 return productService.addProduct(product);		
	}
	
	@PutMapping("/products")
	public Response updateProduct(@RequestBody NewProductDto product) {
	//System.out.println("in controller:"+	product.getSellerId());
		System.out.println("category size:"+product.getCategories().length);
		return productService.updateProduct(product);		
	}
	
//	@PutMapping("/products")
//	public Response updateProduct(@RequestBody NewProductDto product) {
//		 return productService.addProduct(productService.updateImage(product));		
//	}
	
	@PutMapping("/product/statusupdate/{id}")
	public Response updateProductStatus(@RequestBody List<StatusDto> status,@RequestHeader(value="token") String token) {
		// status.setToken(token);
		 //status.setId(Integer.parseInt(id));
		return productService.updateProductStatus(status);		
	}
}
