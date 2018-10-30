package com.nagarro.productmanagement.productManagement.dao;

import com.nagarro.productmanagement.productManagement.dto.NewProductDto;
import com.nagarro.productmanagement.productManagement.dto.Response;

public interface ProductDao {

	public Response getProducts(String id);
	public Response addProduct(NewProductDto newProductDto );
}
