package com.nagarro.productmanagement.productManagement.dao.impl;

import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.nagarro.productmanagement.productManagement.constants.Constants;
import com.nagarro.productmanagement.productManagement.constants.HQLQueries;
import com.nagarro.productmanagement.productManagement.dao.SellerDao;
import com.nagarro.productmanagement.productManagement.dto.ResponseData;
import com.nagarro.productmanagement.productManagement.dto.ResponseDto;
import com.nagarro.productmanagement.productManagement.dto.SellerRegistrationDto;
import com.nagarro.productmanagement.productManagement.models.Seller;
import com.nagarro.productmanagement.productManagement.models.SellerDetails;
import com.nagarro.productmanagement.productManagement.utils.HibernateUtils;

@Component
public class SellerDaoImpl implements SellerDao {

	private Session session;
	
	public SellerDaoImpl(){
		
		this.session=HibernateUtils.createSession();
	}
	
	
	@Override
	public ResponseDto registerSeller(SellerRegistrationDto sellerRegistrationDto) {
		
		ResponseDto responseDto=new ResponseDto();
		
		if(sellerRegistrationDto.getPassword().equals(sellerRegistrationDto.getConfirmPassword())){
		session.beginTransaction();
		
		Seller seller=new Seller();
		seller.setSellername(sellerRegistrationDto.getUsername());
		seller.setSellerpassword(sellerRegistrationDto.getPassword());
		seller.setSellerstatus(Constants.NEED_APPROVAL);
		
		int sellerid=Integer.parseInt(session.save(seller).toString());
		
		
		SellerDetails sellerDetails=new SellerDetails();
		sellerDetails.setCompanyname(sellerRegistrationDto.getCompanyName());
		sellerDetails.setOwnername(sellerRegistrationDto.getOwnerName());
		sellerDetails.setAddress(sellerRegistrationDto.getAddress());
		sellerDetails.setEmail(sellerRegistrationDto.getEmail());
		sellerDetails.setGst(sellerRegistrationDto.getGst());
		sellerDetails.setTelephone(sellerRegistrationDto.getTelephone());
		sellerDetails.setSellerid(sellerid);
		
		
		if(session.save(sellerDetails)!=null){
			ResponseData responseData=new ResponseData();
		responseData.setId(sellerid);
		responseData.setUsername(seller.getSellername().toString());
		responseData.setToken("");
			responseDto.setStatus(200);
			responseDto.setData(responseData);
			
			session.getTransaction().commit();
		
		}
		}
		else{
			System.out.println("password mismatch");
			ResponseData responseData=new ResponseData();
			
			
			responseDto.setStatus(403);
			responseDto.setData(responseData);
			responseDto.setMessage("password mismatch");
			
		}
		
		return responseDto;
		
		
	
	}

}
