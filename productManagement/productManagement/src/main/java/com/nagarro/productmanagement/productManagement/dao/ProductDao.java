package com.nagarro.productmanagement.productManagement.dao;

import com.nagarro.productmanagement.productManagement.dto.ProductDto;
import com.nagarro.productmanagement.productManagement.dto.Response;
import com.nagarro.productmanagement.productManagement.dto.ResponseDto;
import com.nagarro.productmanagement.productManagement.dto.StatusDto;

public interface ProductDao {

	public Response getProducts(String id);
	public Response addProduct(ProductDto newProductDto );
	public Response updateProductStatus(StatusDto status);
}
