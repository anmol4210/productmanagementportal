package com.nagarro.productmanagement.dao.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.productmanagement.constants.Constants;
import com.nagarro.productmanagement.dao.ProductDao;
import com.nagarro.productmanagement.dao.SaveReadImagesDao;
import com.nagarro.productmanagement.dto.NewProductDto;
import com.nagarro.productmanagement.dto.ProductDto;
import com.nagarro.productmanagement.dto.Response;
import com.nagarro.productmanagement.dto.ResponseData;
import com.nagarro.productmanagement.dto.ResponseDto;
import com.nagarro.productmanagement.dto.StatusDto;
import com.nagarro.productmanagement.models.Categories;
import com.nagarro.productmanagement.models.GalleryImages;
import com.nagarro.productmanagement.models.Product;
import com.nagarro.productmanagement.models.Seller;
import com.nagarro.productmanagement.models.SellerDetails;
import com.nagarro.productmanagement.utils.HibernateUtils;

@Component
public class ProductDaoImpl implements ProductDao {
	
	@Autowired
	public SaveReadImagesDao saveReadImageDao;
	
	private Session session;
	
	public ProductDaoImpl() {

		this.session = HibernateUtils.createSession();
		this.session.beginTransaction();
	}
	
	@Override
	public Response getProducts(String id,String sortBy, List<String> status,	String searchType, String searchKeyword) {
		this.session = HibernateUtils.createSession();
		this.session.beginTransaction();
	
		Response<List<NewProductDto>> response=new Response();
		try {
			
			String whereClause = "";
            String sortOrder = "";
            
            if(!Objects.isNull(status)) {
                   whereClause = " AND product.status IN (";
                   
                   int count=0;
                   for(String eachStatus: status) {
                         System.out.println(eachStatus);
                         if(count!=0) {
                                whereClause += ", ";
                         }
                         whereClause += "'"+eachStatus+"'";
                         count++;
                   }
                   
                   whereClause += ")";
            }
            
            if(!Objects.isNull(sortBy)) {
                   sortOrder = " ORDER BY product."+sortBy;
            }

			Query query = this.session.createQuery("FROM Product as product where product.seller.id= :id "
			+whereClause+sortOrder);
		query.setParameter("id",Integer.parseInt(id));
		
		
		List<Product> products=query.list();
		
		if(products.size()>0) {
		
			List<NewProductDto> productList=new ArrayList();
			
		for(Product product:products) {
			
			query = this.session.createQuery("SELECT categories.categoryname FROM Categories as categories where categories.product.id=:id");
			query.setParameter("id", Integer.parseInt(""+product.getId()));
			
			List<String> categoriesList=query.list();
			
			
			String[] categoriesArray = categoriesList.toArray(new String[0]);
			
			query = this.session.createQuery("SELECT images.imageurl FROM GalleryImages as images where images.product.id=:id");
				query.setParameter("id", product.getId());
			
				List<String> galleryList=query.list();
				
				
				String[] galleryImageArray = galleryList.toArray(new String[0]);
				
			
			NewProductDto productDto=new NewProductDto();
			productDto.setCategories(categoriesArray);
			productDto.setGalleryImages(galleryImageArray);
			productDto.setComments(product.getComments());
			productDto.setCreatedat(product.getCreatedat());
			productDto.setDimensions(product.getDimensions());
			productDto.setId(product.getId());
			productDto.setLongdiscription(product.getLongdiscription());
			productDto.setMrp(product.getMrp());
			productDto.setPrimaryimage(product.getPrimaryimage());
			productDto.setProductattributes(product.getProductattributes());
			productDto.setProductname(product.getProductname());
			productDto.setSellerId(product.getSeller().getId());
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
		response.setData(productList);
		}
		else {
			response.setStatus(404);
			response.setMessage("No Product Found");
		}
		}catch(Exception e) {
			System.out.println(e);
			response.setStatus(500);
			response.setMessage(""+e);
		}
		return response;
		
		
	}
	
	@Override
	public Response addProduct(ProductDto newProductDto) {
		
		this.session = HibernateUtils.createSession();
		this.session.beginTransaction();
		Response<String> response=new Response();
		try {
			Seller seller = new Seller();
			seller.setId(newProductDto.getSellerId());
			Product product=new Product();
			System.out.println("1");
		product.setSellerproductcode(newProductDto.getSellerproductcode());
		product.setProductname(newProductDto.getProductname());
		System.out.println("3");
		product.setShortdiscription(newProductDto.getShortdiscription());
		product.setLongdiscription(newProductDto.getLongdiscription());	
		product.setDimensions(newProductDto.getDimensions());
		product.setMrp(newProductDto.getMrp());
		System.out.println("6");
		product.setSsp(newProductDto.getSsp());
		System.out.println("7");
		product.setYmp(newProductDto.getYmp()); 
		product.setPrimaryimage(newProductDto.getPrimaryimage());
		System.out.println("8");
		product.setUsageinstructins(newProductDto.getUsageinstructins());
		product.setStatus(Constants.NEED_APPROVAL);
		
		
		//LocalDateTime currenttime=LocalDateTime.now();
		
		//product.setCreatedat(currenttime);
		//product.setUpdatedat(currenttime);		
		//product.setComments(newProductDto.getComments());
		
		product.setProductattributes(newProductDto.getProductattributes());
		product.setSeller(seller);
	
//		for(String category:newProductDto.getCategories()) {
//			Categories categories=new Categories();
//			categories.setCategoryname(category);
//			categories.setProduct(product);
//			session.save(categories);
//		}
		
//		for(String imageurl:newProductDto.getGalleryImages() ) {
//			GalleryImages galleryImage=new GalleryImages();
//			galleryImage.setImageurl(imageurl);
//			galleryImage.setProduct(product);
//			session.save(galleryImage);
//			
//			
//		}
		
		session.save(product);
		session.getTransaction().commit();
//		if (session.save(product) != null) {
//			ResponseData responseData = new ResponseData();
//			System.out.println("seller id:"+seller.getId());
//			response.setStatus(200);
//			} 
//		else {
//		
//		response.setStatus(400);
//		response.setMessage("Unable to add Data");
//
//	}
		response.setStatus(200);
		
		}
		catch(Exception e) {
		System.out.println(e);
			response.setStatus(400);
			response.setMessage(e.getMessage());
	}
		return response;
	}
	
	
	@Override
	public Response updateProductStatus(List<StatusDto> status) {
		this.session = HibernateUtils.createSession();
		this.session.beginTransaction();
	
		Response response=new Response();
		
		try {
		
			for(StatusDto productStatus:status) {
			Object object = session.load(Product.class,new Integer(""+productStatus.getId()));
			Product product=(Product) object;
			product.setStatus(productStatus.getStatus());
			product.setComments(productStatus.getMessage());
			session.getTransaction().commit();
		}
		
		response.setStatus(200);
			}catch(Exception e) {
				response.setStatus(400);
				response.setMessage(e.getMessage());
				System.out.println(e);
			}
		
		return response;
	}

	@Override
	public Response getProduct(String productid) {
		this.session = HibernateUtils.createSession();
		this.session.beginTransaction();
	
		Response<NewProductDto> response=new Response();
		try {
		Query query = this.session.createQuery("FROM Product as product where product.id= :id ");
		query.setParameter("id",Integer.parseInt(productid));
		List<Product> products=query.list();
		
		NewProductDto productDto=new NewProductDto();
		if(products.size()>0) {
	
		
		for(Product product:products) {
			
			
			query = this.session.createQuery("SELECT categories.categoryname FROM Categories as categories"
					+ " where categories.product.id=:id");
			query.setParameter("id", Integer.parseInt(""+product.getId()));
			
			List<String> categoriesList=query.list();
			
			
			String[] categoriesArray = categoriesList.toArray(new String[0]);
			
			query = this.session.createQuery("SELECT images.imageurl FROM GalleryImages as images where images.product.id=:id");
				query.setParameter("id", product.getId());
			
				List<String> galleryList=query.list();
			
				String[] galleryImageArray = galleryList.toArray(new String[0]);
			
			productDto.setCategories(categoriesArray);
			productDto.setGalleryImages(galleryImageArray);
			productDto.setComments(product.getComments());
			productDto.setCreatedat(product.getCreatedat());
			productDto.setDimensions(product.getDimensions());
			productDto.setId(product.getId());
			productDto.setLongdiscription(product.getLongdiscription());
			productDto.setMrp(product.getMrp());
			productDto.setPrimaryimage(product.getPrimaryimage());
			productDto.setProductattributes(product.getProductattributes());
			productDto.setProductname(product.getProductname());
			productDto.setSellerId(product.getSeller().getId());
			productDto.setSellerproductcode(product.getSellerproductcode());
			productDto.setShortdiscription(product.getShortdiscription());
			productDto.setSsp(product.getSsp());
			productDto.setStatus(product.getStatus());
			productDto.setUpdatedat(product.getUpdatedat());
			productDto.setUsageinstructins(product.getUsageinstructins());
			productDto.setYmp(product.getYmp());
			
		}
		response.setStatus(200);
		//System.out.println("product Set:"+productList.get(1).getCategories()[0]);
		response.setData(productDto);
		}
		else {
			response.setStatus(404);
			response.setMessage("No Product Found");
			}
		}catch(Exception e) {
			System.out.println(e);
			response.setStatus(500);
			response.setMessage(""+e);
		}
		return response;
		
	
	
	
	}

	@Override
	public Response updateProduct(ProductDto newProductDto) {
		System.out.println("updating products.............");
		this.session = HibernateUtils.createSession();
		this.session.beginTransaction();
	
		Response<String> response=new Response();
		try {
		Object object = session.load(Product.class, new Integer(""+newProductDto.getId()));
		Product product = (Product) object;
		//product.setCategories(newProductDto.getCategories());
		//product.setComments(newProductDto.getComments());
		product.setDimensions(newProductDto.getDimensions());
		product.setLongdiscription(newProductDto.getLongdiscription());
		product.setPrimaryimage(newProductDto.getPrimaryimage());
		//product.setGalleryImages(newProductDto.getGalleryImages());
		product.setMrp(newProductDto.getMrp());
		product.setProductattributes(newProductDto.getProductattributes());
		product.setProductname(newProductDto.getProductname());
		
		//product.setSeller(newProductDto.getSellerId());
		product.setSellerproductcode(newProductDto.getSellerproductcode());
		product.setShortdiscription(newProductDto.getShortdiscription());
		product.setSsp(newProductDto.getSsp());
		product.setYmp(newProductDto.getYmp());
		product.setUsageinstructins(newProductDto.getUsageinstructins());
		//product.setStatus(newProductDto.getStatus());
		product.setId(newProductDto.getId());
		//user.setPassword(resetUser.getPassword());
		session.getTransaction().commit();
		response.setStatus(200);
		response.setData("Product updated");
		}catch(Exception e) {
			response.setStatus(400);
			response.setMessage(e.getMessage());
		}
		
		return response;
	}

}
