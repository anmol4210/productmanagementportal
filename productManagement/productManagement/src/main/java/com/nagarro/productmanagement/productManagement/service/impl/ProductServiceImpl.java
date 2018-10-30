package com.nagarro.productmanagement.productManagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.productmanagement.productManagement.dao.ProductDao;
import com.nagarro.productmanagement.productManagement.dao.impl.ProductDaoImpl;
import com.nagarro.productmanagement.productManagement.dto.NewProductDto;
import com.nagarro.productmanagement.productManagement.dto.Response;
import com.nagarro.productmanagement.productManagement.service.ProductService;

@Component
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductDao productDao;
	
	@Override
	public Response getProducts(String id) {
		return productDao.getProducts(id);
		

	}

	@Override
	public Response addProduct(NewProductDto newProductDto) {
		// TODO Auto-generated method stub
		return productDao.addProduct(newProductDto);
	}

}
