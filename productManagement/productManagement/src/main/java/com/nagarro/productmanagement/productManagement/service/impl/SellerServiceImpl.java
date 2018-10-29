package com.nagarro.productmanagement.productManagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.productmanagement.productManagement.dao.SellerDao;
import com.nagarro.productmanagement.productManagement.dto.AdminDto;
import com.nagarro.productmanagement.productManagement.dto.ResponseData;
import com.nagarro.productmanagement.productManagement.dto.ResponseDto;
import com.nagarro.productmanagement.productManagement.dto.SellerRegistrationDto;
import com.nagarro.productmanagement.productManagement.service.SellerService;

@Component
public class SellerServiceImpl implements SellerService{

	@Autowired
	public SellerDao sellerdao;
	
	@Override
	public ResponseDto registerSeller(SellerRegistrationDto seller) {

		return sellerdao.registerSeller(seller);
		
	}

}
