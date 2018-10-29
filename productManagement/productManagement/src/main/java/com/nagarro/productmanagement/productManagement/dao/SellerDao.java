package com.nagarro.productmanagement.productManagement.dao;

import com.nagarro.productmanagement.productManagement.dto.ResponseData;
import com.nagarro.productmanagement.productManagement.dto.ResponseDto;
import com.nagarro.productmanagement.productManagement.dto.SellerRegistrationDto;

public interface SellerDao {

	public ResponseDto registerSeller(SellerRegistrationDto sellerRegistrationDto);
}
