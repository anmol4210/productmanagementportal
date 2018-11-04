package com.nagarro.productmanagement.dto;

import com.nagarro.productmanagement.models.Product;

public class CategoryDto {
	private String id;
	private String category;
	private ProductDto productDto;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public ProductDto getProductDto() {
		return productDto;
	}
	public void setProductDto(ProductDto productDto) {
		this.productDto = productDto;
	}
	
}
