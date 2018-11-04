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
	@Autowired
	ProductFilterDao productFilterDao;
	@Autowired
	SaveReadImagesDao saveReadImageDao;
	
	
	@Override
	public Response getProducts(String sellerid,String sortBy, List<String> status,	String searchType, String searchKeyword) {
		return productDao.getProducts(sellerid,sortBy,status,searchType,searchKeyword);
		

	}

	@Override
	public Response addProduct(NewProductDto newProductDto) {
		
		ProductDto productDto=new ProductDto();
		productDto.setCategories(newProductDto.getCategories());
		productDto.setComments(newProductDto.getComments());
		productDto.setDimensions(newProductDto.getDimensions());
		productDto.setLongdiscription(newProductDto.getLongdiscription());
		productDto.setPrimaryimage(newProductDto.getPrimaryimage());
		productDto.setGalleryImages(newProductDto.getGalleryImages());
		productDto.setMrp(newProductDto.getMrp());
		productDto.setProductattributes(newProductDto.getProductattributes());
		productDto.setProductname(newProductDto.getProductname());
		productDto.setSellerId(newProductDto.getSellerId());
		//productDto.setSellerproductcode(newProductDto.getSellerproductcode());
		productDto.setShortdiscription(newProductDto.getShortdiscription());
		productDto.setSsp(newProductDto.getSsp());
		productDto.setYmp(newProductDto.getYmp());
		productDto.setUsageinstructins(newProductDto.getUsageinstructins());
		productDto.setSellerproductcode(newProductDto.getSellerproductcode());
		return productDao.addProduct(productDto);
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

	@Override
	public Response updateProduct(NewProductDto newProductDto) {
		ProductDto productDto=new ProductDto();
		productDto.setCategories(newProductDto.getCategories());
		//productDto.setComments(newProductDto.getComments());
		productDto.setDimensions(newProductDto.getDimensions());
		productDto.setLongdiscription(newProductDto.getLongdiscription());
		productDto.setPrimaryimage(newProductDto.getPrimaryimage());
		//productDto.setGalleryImages(newProductDto.getGalleryImages());
		productDto.setMrp(newProductDto.getMrp());
		productDto.setProductattributes(newProductDto.getProductattributes());
		productDto.setProductname(newProductDto.getProductname());
		productDto.setSellerId(newProductDto.getSellerId());
		productDto.setSellerproductcode(newProductDto.getSellerproductcode());
		productDto.setShortdiscription(newProductDto.getShortdiscription());
		productDto.setSsp(newProductDto.getSsp());
		productDto.setYmp(newProductDto.getYmp());
		System.out.println("usage instructions:"+newProductDto.getUsageinstructins());
		productDto.setUsageinstructins(newProductDto.getUsageinstructins());
		productDto.setStatus(newProductDto.getStatus());
		productDto.setId(newProductDto.getId());
		return productDao.updateProduct(productDto);
	}

	@Override
	public Response searchProducts(String id, String searchType, String searchKeyword) {
		System.out.println("in service.......");
		return productFilterDao.searchProducts(id, searchType, searchKeyword);
	}

	

}
