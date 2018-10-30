package com.nagarro.productmanagement.productManagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.productmanagement.productManagement.dao.ProductDao;
import com.nagarro.productmanagement.productManagement.dao.impl.ProductDaoImpl;
import com.nagarro.productmanagement.productManagement.dto.ProductDto;
import com.nagarro.productmanagement.productManagement.dto.Response;
import com.nagarro.productmanagement.productManagement.dto.ResponseDto;
import com.nagarro.productmanagement.productManagement.dto.StatusDto;
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
	public Response addProduct(ProductDto newProductDto) {
		// TODO Auto-generated method stub
		return productDao.addProduct(newProductDto);
	}

	@Override
	public Response updateProductStatus(StatusDto status) {
		// TODO Auto-generated method stub
		return productDao.updateProductStatus(status);
	}

}
