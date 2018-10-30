package com.nagarro.productmanagement.productManagement.service;

import java.util.List;

import com.nagarro.productmanagement.productManagement.dto.LoginDto;
import com.nagarro.productmanagement.productManagement.dto.Response;
import com.nagarro.productmanagement.productManagement.dto.ResponseData;
import com.nagarro.productmanagement.productManagement.dto.ResponseDto;
import com.nagarro.productmanagement.productManagement.dto.SellerRegistrationDto;
import com.nagarro.productmanagement.productManagement.dto.SellerDetailsDto;
import com.nagarro.productmanagement.productManagement.dto.StatusDto;

public interface SellerService {

	public ResponseDto registerSeller(SellerRegistrationDto seller);
	public ResponseDto authenticateSeller(LoginDto seller);
	public ResponseDto updateSellerStatus(StatusDto status);
	public Response getAllSellers();
	public Response getSeller(String id);
	public Response updateSeller(SellerDetailsDto sellerDetails,String id);
}
