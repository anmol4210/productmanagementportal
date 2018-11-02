package com.nagarro.productmanagement.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.productmanagement.dao.ProductFilterDao;
import com.nagarro.productmanagement.dao.SaveReadImagesDao;
import com.nagarro.productmanagement.dto.NewProductDto;
import com.nagarro.productmanagement.dto.Response;
import com.nagarro.productmanagement.models.Product;
import com.nagarro.productmanagement.utils.HibernateUtils;

@Component
public class ProductFilterDaoImpl implements ProductFilterDao {

	
	
	@Autowired
	public SaveReadImagesDao saveReadImageDao;
	
	private Session session;
	
	public ProductFilterDaoImpl() {

		this.session = HibernateUtils.createSession();
		this.session.beginTransaction();
	}
	
	@Override
	public Response getAllProduct(String sortBy, List<String> status, String searchKeyword, String searchType) {
		
		String whereClause = "";
        String sortOrder =" ORDER BY FIELD(product.status, 'NEED_APPROVAL','APPROVED','REJECTED')";
        
        if(sortBy!=null) {
        		sortOrder="ORDER BY product."+sortBy;
        }  
        
        if(status!=null && status.size()>0) {
        	whereClause = " WHERE product.status IN (";
            int count=0;   
        	for(String column: status) {
        		if(count!=0) {
        			whereClause +=",";
        		}
            	   whereClause +="'"+ column+"'";
            	   count++;
               }
        	whereClause +=")";
        }
        if(searchKeyword!=null) {
        if(searchKeyword.equals("sellerid")) {
        	whereClause = " WHERE product.sellerid ="+searchType;
        }
        else if(searchKeyword.equals("productid")) {
        	System.out.println("comparing product id......");
        	whereClause = " WHERE product.id ="+searchType;
        }
        else if(searchKeyword.equals("sellerproductcode")) {
        	whereClause = " WHERE product.sellerproductcode ="+searchType;
        }
        else if(searchKeyword.equals("companyname")) {
        	whereClause = " WHERE product.companyname LIKE '%"+searchType+"%'";
        }
        else if(searchKeyword.equals("productname")) {
        	whereClause = " WHERE product.productname LIKE '%"+searchType+"%'";
        }}
        
        
        
		Response<List<NewProductDto>> response=new Response();
		try {
			Query query = this.session.createQuery("FROM Product as product "+whereClause + sortOrder);
		List<Product> products=query.list();
		if(products.size()>0) {
		List<NewProductDto> productList=new ArrayList();
			
		for(Product product:products) {
			List<String> galleryList=null;
			byte[][] galleryImageArray=null;
			byte[] primaryImage=null;
			query = this.session.createQuery("SELECT categories.categoryname FROM Categories as categories where categories.product.id=:id");
			query.setParameter("id", Integer.parseInt(""+product.getId()));
			
			List<String> categoriesList=query.list();
			
			System.out.println("categories size...."+categoriesList.size());
			String[] categoriesArray = categoriesList.toArray(new String[0]);
			
			query = this.session.createQuery("SELECT images.imageurl FROM GalleryImages as images where images.product.id=:id");
				query.setParameter("id", product.getId());
			
				 galleryList=query.list();
				System.out.println("gallery size......"+galleryList.size());
				if(galleryList.size()>0) {
				 galleryImageArray = new byte[galleryList.size()][];
				
				for(int index=0;index<galleryList.size();index++) {
					galleryImageArray[index]=saveReadImageDao.readImage(galleryList.get(index));
				}
				}
				System.out.println("primary......");
				if(product.getPrimaryimage()!=null) {
				// primaryImage=saveReadImageDao.readImage(product.getPrimaryimage());
					}
			System.out.println("after primary......");
			
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

}
