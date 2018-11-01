package com.nagarro.productmanagement.dao;

import com.nagarro.productmanagement.dto.NewProductDto;
import com.nagarro.productmanagement.dto.ProductDto;

public interface SaveReadImagesDao {
	public ProductDto saveImage(NewProductDto newProductDto);
	
	public byte[] readImage(String path);
}
