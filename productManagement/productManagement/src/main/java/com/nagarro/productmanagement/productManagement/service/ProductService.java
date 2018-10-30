package com.nagarro.productmanagement.productManagement.service;

import com.nagarro.productmanagement.productManagement.dto.ProductDto;
import com.nagarro.productmanagement.productManagement.dto.Response;
import com.nagarro.productmanagement.productManagement.dto.ResponseDto;
import com.nagarro.productmanagement.productManagement.dto.StatusDto;

public interface ProductService {
	public Response getProducts(String id);
	public Response addProduct(ProductDto newProductDto);
	public Response updateProductStatus(StatusDto status);
}
