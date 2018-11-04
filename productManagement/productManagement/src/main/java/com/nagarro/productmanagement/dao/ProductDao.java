package com.nagarro.productmanagement.dao;

import java.util.List;

import com.nagarro.productmanagement.dto.ProductDto;
import com.nagarro.productmanagement.dto.Response;
import com.nagarro.productmanagement.dto.ResponseDto;
import com.nagarro.productmanagement.dto.StatusDto;

public interface ProductDao {
	public Response getProduct(String productid);
	public Response getProducts(String id,String sortBy, List<String> status,	String searchType, String searchKeyword);
	public Response addProduct(ProductDto newProductDto );
	public Response updateProduct(ProductDto newProductDto );
	public Response updateProductStatus(List<StatusDto> status);
}
