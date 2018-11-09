package com.nagarro.productmanagement.dao;

import com.nagarro.productmanagement.dto.CategoryDto;
import com.nagarro.productmanagement.dto.Response;

public interface CategoryDao {

	public Response getAllCategories();
	public Response getProductCategories(String productId);
	public Response addNewCategory(CategoryDto categoryDto);
	public Response updateCategory(CategoryDto categoryDto);
	public Response deleteCategory(CategoryDto categoryDto);
	public Response getCategoryProducts(String categoryname);
}
