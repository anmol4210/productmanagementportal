package com.nagarro.productmanagement.productManagement.dao;

import java.util.List;

import com.nagarro.productmanagement.productManagement.dto.LoginDto;
import com.nagarro.productmanagement.productManagement.dto.Response;
import com.nagarro.productmanagement.productManagement.dto.ResponseData;
import com.nagarro.productmanagement.productManagement.dto.ResponseDto;
import com.nagarro.productmanagement.productManagement.dto.SellerRegistrationDto;
import com.nagarro.productmanagement.productManagement.dto.SellerDetailsDto;
import com.nagarro.productmanagement.productManagement.dto.StatusDto;

public interface SellerDao {

	public ResponseDto registerSeller(SellerRegistrationDto sellerRegistrationDto);
	public ResponseDto authenticateSeller(LoginDto sellerLoginDto);
	public ResponseDto updateSellerStatus(List<StatusDto> status);
	public Response getAllSellers(List<String> sortBy, String status);
	public Response getSeller(String id);
	public Response updateSeller(SellerDetailsDto sellerDetails, String id);
}
