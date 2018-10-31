package com.nagarro.productmanagement.productManagement.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.productmanagement.productManagement.dao.ProductDao;
import com.nagarro.productmanagement.productManagement.dao.impl.ProductDaoImpl;
import com.nagarro.productmanagement.productManagement.dto.NewProductDto;
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

	@Override
	public ProductDto saveImage(NewProductDto newProductDto) {
		List<String> imageUrl=new ArrayList();
		ProductDto productDto=new ProductDto();
		try {
		byte[][] images=newProductDto.getGalleryImages();
		int index=0;
	    File directory = new File("D:\\images\\"+newProductDto.getProductname());
	    if (! directory.exists()){
	        directory.mkdir();
	        // If you require it to make the entire directory path including parents,
	        // use directory.mkdirs(); here instead.
	    }
	    String primaryImagePath="D:\\images\\"+newProductDto.getProductname()+"\\primaryImage"+".png";
	   
	    
	    FileOutputStream fileOutputStream=new FileOutputStream(primaryImagePath);    
		fileOutputStream.write(newProductDto.getPrimaryimage());    
		fileOutputStream.close();   
	

		for(byte[] image:images) {
			String galleryImagePath= "D:\\images\\"+newProductDto.getProductname()+"\\"+index+".png";
		
			
			fileOutputStream=new FileOutputStream(galleryImagePath);    
			fileOutputStream.write(image);    
			fileOutputStream.close();   
			index++;
			imageUrl.add(galleryImagePath);
		}
		
		
		
	
		
		productDto.setCategories(newProductDto.getCategories());
		productDto.setComments(newProductDto.getComments());
		productDto.setCreatedat(newProductDto.getCreatedat());
		productDto.setDimensions(newProductDto.getDimensions());
		productDto.setGalleryImages(imageUrl.toArray(new String[0]));
		productDto.setId(newProductDto.getId());
		productDto.setLongdiscription(newProductDto.getLongdiscription());
		productDto.setPrimaryimage(primaryImagePath.toString());
		productDto.setGalleryImages(imageUrl.toArray(new String[0]));
		productDto.setMrp(newProductDto.getMrp());
		productDto.setProductattributes(newProductDto.getProductattributes());
		productDto.setProductname(newProductDto.getProductname());
		productDto.setSellerId(newProductDto.getSellerId());
		productDto.setSellerproductcode(newProductDto.getSellerproductcode());
		productDto.setShortdiscription(newProductDto.getShortdiscription());
		productDto.setSsp(newProductDto.getSsp());
		productDto.setYmp(newProductDto.getYmp());
		productDto.setStatus(newProductDto.getStatus());
		productDto.setUpdatedat(newProductDto.getUpdatedat());
		productDto.setUsageinstructins(newProductDto.getUsageinstructins());
		
		
		
		
		
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return productDto;
	}

}
