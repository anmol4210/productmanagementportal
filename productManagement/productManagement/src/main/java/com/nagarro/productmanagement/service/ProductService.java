package com.nagarro.productmanagement.service;

import java.util.List;

import com.nagarro.productmanagement.dto.NewProductDto;
import com.nagarro.productmanagement.dto.ProductDto;
import com.nagarro.productmanagement.dto.Response;
import com.nagarro.productmanagement.dto.ResponseDto;
import com.nagarro.productmanagement.dto.StatusDto;


public interface ProductService {
	public Response getProduct(String productid);
	public Response getProducts(String sellerid);
	public Response addProduct(NewProductDto newProductDto);
	public Response updateProduct(NewProductDto newProductDto);
	public Response updateProductStatus(List<StatusDto> status);
	public ProductDto saveImage(NewProductDto productDto); 
	public ProductDto updateImage(NewProductDto productDto);
	public Response getAllProducts(String sortBy, List<String> status,	String searchType, String searchKeyword);
	
}
