package com.nagarro.productmanagement.productManagement.service;

import java.util.List;

import com.nagarro.productmanagement.productManagement.dto.LoginDto;
import com.nagarro.productmanagement.productManagement.dto.ResponseData;
import com.nagarro.productmanagement.productManagement.dto.ResponseDto;
import com.nagarro.productmanagement.productManagement.dto.SellerRegistrationDto;
import com.nagarro.productmanagement.productManagement.dto.SellerResponseDto;
import com.nagarro.productmanagement.productManagement.dto.StatusDto;

public interface SellerService {

	public ResponseDto registerSeller(SellerRegistrationDto seller);
	public ResponseDto authenticateSeller(LoginDto seller);
	public ResponseDto updateSellerStatus(StatusDto status);
	public List<SellerResponseDto> getAllSellers();
	public SellerResponseDto getSeller(String id);
}
