package com.nagarro.productmanagement.dao;

import com.nagarro.productmanagement.dto.ProductDto;
import com.nagarro.productmanagement.dto.Response;
import com.nagarro.productmanagement.dto.ResponseDto;
import com.nagarro.productmanagement.dto.StatusDto;

public interface ProductDao {

	public Response getProducts(String id);
	public Response addProduct(ProductDto newProductDto );
	public Response updateProductStatus(StatusDto status);
}
