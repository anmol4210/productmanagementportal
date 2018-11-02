package com.nagarro.productmanagement.dao.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
	public Response getProducts(String id) {
		Response<List<NewProductDto>> response=new Response();
		try {
			
			Query query = this.session.createQuery("FROM Product as product where product.seller.id= :id ");
		query.setParameter("id",Integer.parseInt(id));
		List<Product> products=query.list();
		
		if(products.size()>0) {
		List<NewProductDto> productList=new ArrayList();
			
		for(Product product:products) {
			
			System.out.println("Product Id:"+product.getId());
			
			query = this.session.createQuery("SELECT categories.categoryname FROM Categories as categories where categories.product.id=:id");
			query.setParameter("id", Integer.parseInt(""+product.getId()));
			
			List<String> categoriesList=query.list();
			
			
			String[] categoriesArray = categoriesList.toArray(new String[0]);
			System.out.println("categories size:"+categoriesArray.length);
			
			query = this.session.createQuery("SELECT images.imageurl FROM GalleryImages as images where images.product.id=:id");
				query.setParameter("id", product.getId());
			
				List<String> galleryList=query.list();
				
				
				byte[][] galleryImageArray = new byte[galleryList.size()][];
				
				for(int index=0;index<galleryList.size();index++) {
					galleryImageArray[index]=saveReadImageDao.readImage(galleryList.get(index));
				}
				//galleryList.toArray(new String[0]);
				
				byte[] primaryImage=saveReadImageDao.readImage(product.getPrimaryimage());
			
			
			NewProductDto productDto=new NewProductDto();
			productDto.setCategories(categoriesArray);
			productDto.setGalleryImages(galleryImageArray);
			productDto.setComments(product.getComments());
			productDto.setCreatedat(product.getCreatedat());
			productDto.setDimensions(product.getDimensions());
			productDto.setId(product.getId());
			productDto.setLongdiscription(product.getLongdiscription());
			productDto.setMrp(product.getMrp());
			productDto.setPrimaryimage(primaryImage);
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
		//System.out.println("product Set:"+productList.get(1).getCategories()[0]);
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
		
		Response<String> response=new Response();
		try {
		//	System.out.println("");
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
		
		for(String category:newProductDto.getCategories()) {
			Categories categories=new Categories();
			categories.setCategoryname(category);
			categories.setProduct(product);
			session.save(categories);
		}
		
		for(String imageurl:newProductDto.getGalleryImages() ) {
			GalleryImages galleryImage=new GalleryImages();
			galleryImage.setImageurl(imageurl);
			galleryImage.setProduct(product);
			session.save(galleryImage);
			
			
		}
		
		if (session.save(product) != null) {
			ResponseData responseData = new ResponseData();
			System.out.println("seller id:"+seller.getId());
			response.setStatus(200);
			//responseDto.setData(responseData);

			session.getTransaction().commit();

	
	} else {
		
		response.setStatus(400);
		response.setMessage("Unable to add Data");

	}
		}
		catch(Exception e) {
			response.setStatus(400);
			response.setMessage(e.getMessage());
	}
		return response;
	}
	
	
	@Override
	public Response updateProductStatus(List<StatusDto> status) {
		
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
		
		Response<NewProductDto> response=new Response();
		try {
		Query query = this.session.createQuery("FROM Product as product where product.id= :id ");
		query.setParameter("id",Integer.parseInt(productid));
		List<Product> products=query.list();
		
		NewProductDto productDto=new NewProductDto();
		if(products.size()>0) {
		
		
		
		
		for(Product product:products) {
			
			//System.out.println("Product Id:"+product.getId());
			
			query = this.session.createQuery("SELECT categories.categoryname FROM Categories as categories"
					+ " where categories.product.id=:id");
			query.setParameter("id", Integer.parseInt(""+product.getId()));
			
			List<String> categoriesList=query.list();
			
			
			String[] categoriesArray = categoriesList.toArray(new String[0]);
		//	System.out.println("categories size:"+categoriesArray.length);
			
			query = this.session.createQuery("SELECT images.imageurl FROM GalleryImages as images where images.product.id=:id");
				query.setParameter("id", product.getId());
			
				List<String> galleryList=query.list();
			
				byte[][] galleryImageArray = new byte[galleryList.size()][];
				
				for(int index=0;index<galleryList.size();index++) {
					galleryImageArray[index]=saveReadImageDao.readImage(galleryList.get(index));
				}
				//galleryList.toArray(new String[0]);
				
				byte[] primaryImage=saveReadImageDao.readImage(product.getPrimaryimage());
			
			
			productDto.setCategories(categoriesArray);
			productDto.setGalleryImages(galleryImageArray);
			productDto.setComments(product.getComments());
			productDto.setCreatedat(product.getCreatedat());
			productDto.setDimensions(product.getDimensions());
			productDto.setId(product.getId());
			productDto.setLongdiscription(product.getLongdiscription());
			productDto.setMrp(product.getMrp());
			productDto.setPrimaryimage(primaryImage);
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

}
