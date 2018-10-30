package com.nagarro.productmanagement.productManagement.service;

import com.nagarro.productmanagement.productManagement.dto.NewProductDto;
import com.nagarro.productmanagement.productManagement.dto.Response;

public interface ProductService {
	public Response getProducts(String id);
	public Response addProduct(NewProductDto newProductDto);
}
