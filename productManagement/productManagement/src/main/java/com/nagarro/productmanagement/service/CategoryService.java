package com.nagarro.productmanagement.service;

import com.nagarro.productmanagement.dto.CategoryDto;
import com.nagarro.productmanagement.dto.Response;

public interface CategoryService {
	public Response getAllCategories();
	public Response getProductCategories(String productId);
	public Response addCategory(CategoryDto categoryDto);
	public Response updateCategory(CategoryDto categoryDto);
	public Response deleteCategory(CategoryDto categoryDto);
	public Response getCategoryProducts(String categoryname);
}
