package com.nagarro.productmanagement.productManagement.service;

import com.nagarro.productmanagement.productManagement.dto.AdminDto;
import com.nagarro.productmanagement.productManagement.dto.ResponseData;
import com.nagarro.productmanagement.productManagement.dto.ResponseDto;
import com.nagarro.productmanagement.productManagement.dto.SellerRegistrationDto;

public interface SellerService {

	public ResponseDto registerSeller(SellerRegistrationDto seller);
}
