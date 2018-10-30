package com.nagarro.productmanagement.productManagement.dao.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.nagarro.productmanagement.productManagement.constants.Constants;
import com.nagarro.productmanagement.productManagement.dao.ProductDao;
import com.nagarro.productmanagement.productManagement.dto.NewProductDto;
import com.nagarro.productmanagement.productManagement.dto.ProductDto;
import com.nagarro.productmanagement.productManagement.dto.Response;
import com.nagarro.productmanagement.productManagement.dto.ResponseData;
import com.nagarro.productmanagement.productManagement.models.Product;
import com.nagarro.productmanagement.productManagement.models.Seller;
import com.nagarro.productmanagement.productManagement.models.SellerDetails;
import com.nagarro.productmanagement.productManagement.utils.HibernateUtils;

@Component
public class ProductDaoImpl implements ProductDao {
	
	
	private Session session;
	
	public ProductDaoImpl() {

		this.session = HibernateUtils.createSession();
		this.session.beginTransaction();
	}
	@Override
	public Response getProducts(String id) {
		Response<List<ProductDto>> response=new Response();
		try {
		Query query = this.session.createQuery("FROM Product");
		
		List<Product> products=query.list();
		
		if(products.size()>0) {
		List<ProductDto> productList=new ArrayList();
			
		for(Product product:products) {
			ProductDto productDto=new ProductDto();
			productDto.setComments(product.getComments());
			productDto.setCreatedat(product.getCreatedat());
			productDto.setDimensions(product.getDimensions());
			productDto.setId(product.getId());
			productDto.setLongdiscription(product.getLongdiscription());
			productDto.setMrp(product.getMrp());
			productDto.setPrimaryimage(product.getPrimaryimage());
			productDto.setProductattributes(product.getProductattributes());
			productDto.setProductname(product.getProductname());
			productDto.setSeller(product.getSeller());
			productDto.setSellerproductcode(product.getSellerproductcode());
			productDto.setShortdiscription(product.getShortdiscription());
			productDto.setSsp(product.getSsp());
			productDto.setStatus(product.getStatus());
			productDto.setUpdatedat(product.getUpdatedat());
			productDto.setUsageinstructins(product.getUsageinstructins());
			productDto.setYmp(product.getYmp());
			productList.add(productDto);
		}
		response.setStatus(200);
		}
		else {
			response.setStatus(404);
			response.setMessage("No Product Found");
		}
		}catch(Exception e) {
			response.setStatus(500);
			response.setMessage(e.getMessage());
		}
		return response;
		
		
	}
	@Override
	public Response addProduct(NewProductDto newProductDto) {
		
		Response<String> response=new Response();
		try {
			System.out.println("");
		Seller seller = new Seller();
		seller.setId(newProductDto.getSellerId());
		//int sellerid = Integer.parseInt(session.save(seller).toString());
		Product product=new Product();
		
		product.setSellerproductcode(newProductDto.getSellerproductcode());
		
		
		product.setProductname(newProductDto.getProductname());
		product.setShortdiscription(newProductDto.getShortdiscription());
		product.setLongdiscription(newProductDto.getLongdiscription());	
		product.setDimensions(newProductDto.getDimensions());
		product.setMrp(newProductDto.getMrp());
		product.setSsp(newProductDto.getSsp());
		product.setYmp(newProductDto.getYmp()); 
		product.setPrimaryimage(newProductDto.getPrimaryimage());
		product.setUsageinstructins(newProductDto.getUsageinstructins());
		product.setStatus(Constants.NEED_APPROVAL);
		LocalDateTime currenttime=LocalDateTime.now();
		
		//product.setCreatedat(currenttime);
		//product.setUpdatedat(currenttime);		
		product.setComments(newProductDto.getComments());
		
		product.setProductattributes(newProductDto.getProductattributes());
		//sellerDetails.setSellerid(sellerid);

		product.setSeller(seller);
		
		if (session.save(product) != null) {
			ResponseData responseData = new ResponseData();
			System.out.println("seller id:"+seller.getId());
			response.setStatus(200);
			//responseDto.setData(responseData);

			session.getTransaction().commit();

	
	} else {
		
		response.setStatus(400);
		//response.setData(responseData);
		response.setMessage("Unable to add Data");

	}
		}
		catch(Exception e) {
			response.setStatus(400);
			//response.setData(responseData);
			response.setMessage(e.getMessage());
	}
		return response;
	}

}
