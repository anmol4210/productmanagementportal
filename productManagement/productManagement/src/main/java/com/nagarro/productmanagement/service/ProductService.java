package com.nagarro.productmanagement.service;

import com.nagarro.productmanagement.dto.NewProductDto;
import com.nagarro.productmanagement.dto.ProductDto;
import com.nagarro.productmanagement.dto.Response;
import com.nagarro.productmanagement.dto.ResponseDto;
import com.nagarro.productmanagement.dto.StatusDto;

public interface ProductService {
	public Response getProducts(String id);
	public Response addProduct(ProductDto newProductDto);
	public Response updateProductStatus(StatusDto status);
	public ProductDto saveImage(NewProductDto productDto); 
	public ProductDto updateImage(NewProductDto productDto);
	
}
