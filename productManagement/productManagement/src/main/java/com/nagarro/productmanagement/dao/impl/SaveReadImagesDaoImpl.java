package com.nagarro.productmanagement.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.nagarro.productmanagement.dao.SaveReadImagesDao;
import com.nagarro.productmanagement.dto.NewProductDto;
import com.nagarro.productmanagement.dto.ProductDto;
import com.nagarro.productmanagement.utils.HibernateUtils;

@Component
public class SaveReadImagesDaoImpl implements SaveReadImagesDao {

	private Session session;
	
	public SaveReadImagesDaoImpl(){
		
		this.session=HibernateUtils.createSession();
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


	@Override
	public byte[] readImage(String path) {
		File file = new File("inputfile.txt");
		
		        FileInputStream fin = null;
		        try {
		            fin = new FileInputStream(file);
		            byte fileContent[] = new byte[(int)file.length()];
		            fin.read(fileContent);
		            return fileContent;

	}catch(Exception e) {
		return null;
	}

}}
