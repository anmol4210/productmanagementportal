package com.nagarro.productmanagement.dao;

import java.util.List;

import com.nagarro.productmanagement.dto.Response;

public interface ProductFilterDao {
	public Response getAllProduct(String sortBy, List<String> status,String searchType, String searchKeyword);
	public Response searchProducts(String id,String searchType, String searchKeyword);
	
}
