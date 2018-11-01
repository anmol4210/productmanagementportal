package com.nagarro.productmanagement.dao;

import java.util.List;

import com.nagarro.productmanagement.dto.LoginDto;
import com.nagarro.productmanagement.dto.Response;
import com.nagarro.productmanagement.dto.ResponseData;
import com.nagarro.productmanagement.dto.ResponseDto;
import com.nagarro.productmanagement.dto.SellerDetailsDto;
import com.nagarro.productmanagement.dto.SellerRegistrationDto;
import com.nagarro.productmanagement.dto.StatusDto;

public interface SellerDao {

	public ResponseDto registerSeller(SellerRegistrationDto sellerRegistrationDto);
	public ResponseDto authenticateSeller(LoginDto sellerLoginDto);
	public ResponseDto updateSellerStatus(List<StatusDto> status);
	public Response getAllSellers(List<String> sortBy, String status);
	public Response getSeller(String id);
	public Response updateSeller(SellerDetailsDto sellerDetails, String id);
}
