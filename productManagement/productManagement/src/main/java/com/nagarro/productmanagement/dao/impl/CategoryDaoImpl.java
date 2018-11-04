package com.nagarro.productmanagement.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.nagarro.productmanagement.dao.CategoryDao;
import com.nagarro.productmanagement.dto.CategoryDto;
import com.nagarro.productmanagement.dto.NewProductDto;
import com.nagarro.productmanagement.dto.ProductDto;
import com.nagarro.productmanagement.dto.Response;
import com.nagarro.productmanagement.models.Categories;
import com.nagarro.productmanagement.models.Product;
import com.nagarro.productmanagement.utils.HibernateUtils;

@Component
public class CategoryDaoImpl implements CategoryDao {

private Session session;
	
	public CategoryDaoImpl() {

		this.session = HibernateUtils.createSession();
		this.session.beginTransaction();
	}
	
	@Override
	public Response getAllCategories() {
		this.session = HibernateUtils.createSession();
		this.session.beginTransaction();
	
		Response<List<String>> response=new Response();
		try {
		Query query = this.session.createQuery("Select distinct (categories.categoryname) FROM Categories as categories");
			List<String> categories=query.list();
			if(categories.size()>0) {
			response.setData(categories);
			response.setStatus(200);
			
			}
			else {
				response.setStatus(404);
				response.setMessage("No Category created");
			}
		}catch(Exception e) {
			response.setStatus(400);
			response.setMessage(e.getMessage());
		}
		
		return response;
	}

	@Override
	public Response addNewCategory(CategoryDto categoryDto) {
		this.session = HibernateUtils.createSession();
		this.session.beginTransaction();
		Response<String> response=new Response();
	try {
		Categories categories=new Categories();
	categories.setCategoryname(categoryDto.getCategory());
		session.save(categoryDto);
		session.getTransaction().commit();
		response.setStatus(200);
		
	}catch(Exception e) {
		response.setStatus(400);
		response.setMessage(e.getMessage());
	}
		return response;
	}

	@Override
	public Response updateCategory(CategoryDto categoryDto) {
		
		return null;
	}

	@Override
	public Response getProductCategories(String productId) {
		this.session = HibernateUtils.createSession();
		this.session.beginTransaction();
	
		Response<List<CategoryDto>> response=new Response();
		try {
		Query query = this.session.createQuery("FROM Categories as categories where categories.product.id=:productid ");
		query.setParameter("productid", Integer.parseInt(productId))	;
		List<CategoryDto> categories=query.list();
			if(categories.size()>0) {
			response.setData(categories);
			response.setStatus(200);
			
			}
			else {
				response.setStatus(404);
				response.setMessage("No Category created");
			}
		}catch(Exception e) {
			response.setStatus(400);
			response.setMessage(e.getMessage());
		}
		
		return response;
	
	}

}
