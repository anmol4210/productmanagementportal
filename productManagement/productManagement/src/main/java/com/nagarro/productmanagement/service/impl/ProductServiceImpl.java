package com.nagarro.productmanagement.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.productmanagement.dao.ProductDao;
import com.nagarro.productmanagement.dao.ProductFilterDao;
import com.nagarro.productmanagement.dao.SaveReadImagesDao;
import com.nagarro.productmanagement.dao.impl.ProductDaoImpl;
import com.nagarro.productmanagement.dao.impl.ProductFilterDaoImpl;
import com.nagarro.productmanagement.dto.NewProductDto;
import com.nagarro.productmanagement.dto.ProductDto;
import com.nagarro.productmanagement.dto.Response;
import com.nagarro.productmanagement.dto.ResponseDto;
import com.nagarro.productmanagement.dto.StatusDto;
import com.nagarro.productmanagement.service.ProductService;

@Component
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductDao productDao;
	ProductFilterDao productFilterDao;
	SaveReadImagesDao saveReadImageDao;
	
	
	@Override
	public Response getProducts(String sellerid) {
		return productDao.getProducts(sellerid);
		

	}

	@Override
	public Response addProduct(ProductDto newProductDto) {
		// TODO Auto-generated method stub
		return productDao.addProduct(newProductDto);
	}

	@Override
	public Response updateProductStatus(List<StatusDto> status) {
		// TODO Auto-generated method stub
		return productDao.updateProductStatus(status);
	}

	@Override
	public ProductDto saveImage(NewProductDto newProductDto) {
			return saveReadImageDao.saveImage(newProductDto);
	}

	@Override
	public ProductDto updateImage(NewProductDto productDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getProduct(String productid) {
		// TODO Auto-generated method stub
		return productDao.getProduct(productid);
	}

	@Override
	public Response getAllProducts(String sortBy, List<String> status,String searchType, String searchKeyword) {
		System.out.println("in service impl............");
		ProductFilterDaoImpl filter=new ProductFilterDaoImpl();
	//	filter.getAllProduct();
		return filter.getAllProduct( sortBy, status,searchType,searchKeyword);
	}

}
