package com.nagarro.productmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.productmanagement.dao.CategoryDao;
import com.nagarro.productmanagement.dto.CategoryDto;
import com.nagarro.productmanagement.dto.Response;
import com.nagarro.productmanagement.service.CategoryService;

@Component
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao categoryDao;
	
	@Override
	public Response getAllCategories() {
		return categoryDao.getAllCategories();
		
	}

	@Override
	public Response getProductCategories(String productId) {
		
		return categoryDao.getProductCategories(productId);
	}

	@Override
	public Response addCategory(CategoryDto categoryDto) {
		
		return addCategory(categoryDto);
	}

	@Override
	public Response updateCategory(CategoryDto categoryDto) {
	
		return addCategory(categoryDto);
	}

}
