package com.nagarro.productmanagement.productManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.productmanagement.productManagement.dto.AdminDto;
import com.nagarro.productmanagement.productManagement.dto.ResponseData;
import com.nagarro.productmanagement.productManagement.dto.ResponseDto;
import com.nagarro.productmanagement.productManagement.dto.SellerRegistrationDto;
import com.nagarro.productmanagement.productManagement.service.SellerService;

@RestController
public class SellerController {

	@Autowired
	private SellerService sellerService;
	
	@PostMapping("/register/seller")
	public ResponseDto registerSeller(@RequestBody SellerRegistrationDto seller) {
		 return sellerService.registerSeller(seller);		
	}

}
