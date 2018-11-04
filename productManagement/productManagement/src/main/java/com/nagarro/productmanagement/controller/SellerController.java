package com.nagarro.productmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.productmanagement.dto.LoginDto;
import com.nagarro.productmanagement.dto.Response;
import com.nagarro.productmanagement.dto.ResponseData;
import com.nagarro.productmanagement.dto.ResponseDto;
import com.nagarro.productmanagement.dto.SellerDetailsDto;
import com.nagarro.productmanagement.dto.SellerRegistrationDto;
import com.nagarro.productmanagement.dto.StatusDto;
import com.nagarro.productmanagement.service.SellerService;

@RestController
@CrossOrigin
public class SellerController {

	@Autowired
	private SellerService sellerService;
	
	@PostMapping("/seller/register")
	public ResponseDto registerSeller(@RequestBody SellerRegistrationDto seller) {
		System.out.println("controller gst:"+seller.getGst());
		 return sellerService.registerSeller(seller);		
	}
	
	
	@PostMapping("/seller/login")
	public ResponseDto authenticateSeller(@RequestBody LoginDto seller) {
		 return sellerService.authenticateSeller(seller);		
	}
	
	@PutMapping("/seller/statusupdate")
	public ResponseDto updateSellerStatus(@RequestBody List<StatusDto> status,@RequestHeader(value="token") String token) {
		// status.setToken(token);
		// status.setId(Integer.parseInt(id));
		return sellerService.updateSellerStatus(status);		
	}
	
	@GetMapping("/seller")
	public Response getAllSeller(
			@RequestParam(value="sortBy", required=false) String sortBy,
			@RequestParam(value="status", required=false) List<String> status,
			 @RequestParam(value="searchBy", required=false) String searchType,
			@RequestParam(value="keyword", required=false) String searchKeyword) {
		
		System.out.println("keyword:"+searchKeyword);
		System.out.println("search By"+searchType);
		return sellerService.getAllSellers( sortBy,  status, searchKeyword, searchType);		
	}
	
	@GetMapping("/seller/{id}")
	public Response getSeller(@PathVariable String id) {
		//System.out.println("id:"+id);
		 return sellerService.getSeller(id);		
	}
	
	@PutMapping("/seller/{id}")
	public Response updateSeller(@PathVariable String id,@RequestBody SellerDetailsDto sellerDetails) {
		return sellerService.updateSeller(sellerDetails,id);		
	}
	
	

}
