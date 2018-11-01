package com.nagarro.productmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.productmanagement.dao.SellerDao;
import com.nagarro.productmanagement.dto.LoginDto;
import com.nagarro.productmanagement.dto.Response;
import com.nagarro.productmanagement.dto.ResponseData;
import com.nagarro.productmanagement.dto.ResponseDto;
import com.nagarro.productmanagement.dto.SellerDetailsDto;
import com.nagarro.productmanagement.dto.SellerRegistrationDto;
import com.nagarro.productmanagement.dto.StatusDto;
import com.nagarro.productmanagement.service.SellerService;

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
	public ResponseDto updateSellerStatus(List<StatusDto> status) {
		
		return sellerdao.updateSellerStatus(status);
	}

	@Override
	public Response getAllSellers(List<String> sortBy, String status) {
		return sellerdao.getAllSellers( sortBy,  status);
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
