package com.nagarro.productmanagement.productManagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.productmanagement.productManagement.dao.SellerDao;
import com.nagarro.productmanagement.productManagement.dto.LoginDto;
import com.nagarro.productmanagement.productManagement.dto.Response;
import com.nagarro.productmanagement.productManagement.dto.ResponseData;
import com.nagarro.productmanagement.productManagement.dto.ResponseDto;
import com.nagarro.productmanagement.productManagement.dto.SellerRegistrationDto;
import com.nagarro.productmanagement.productManagement.dto.SellerDetailsDto;
import com.nagarro.productmanagement.productManagement.dto.StatusDto;
import com.nagarro.productmanagement.productManagement.service.SellerService;

@Component
public class SellerServiceImpl implements SellerService{

	@Autowired
	public SellerDao sellerdao;
	
	@Override
	public ResponseDto registerSeller(SellerRegistrationDto seller) {

		return sellerdao.registerSeller(seller);
		
	}

	@Override
	public ResponseDto authenticateSeller(LoginDto seller) {

		
		return sellerdao.authenticateSeller(seller);
	}

	@Override
	public ResponseDto updateSellerStatus(StatusDto status) {
		
		return sellerdao.updateSellerStatus(status);
	}

	@Override
	public Response getAllSellers() {
		return sellerdao.getAllSellers();
	}

	@Override
	public Response getSeller(String id) {
		return sellerdao.getSeller(id);
	}

	@Override
	public Response updateSeller(SellerDetailsDto sellerDetails, String id) {
		return sellerdao.updateSeller(sellerDetails, id);
	}

}
