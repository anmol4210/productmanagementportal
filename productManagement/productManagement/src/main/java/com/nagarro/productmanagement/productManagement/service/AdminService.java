package com.nagarro.productmanagement.productManagement.service;

import com.nagarro.productmanagement.productManagement.dto.LoginDto;
import com.nagarro.productmanagement.productManagement.dto.ResponseDto;
import com.nagarro.productmanagement.productManagement.models.Admin;

public interface AdminService {

	public ResponseDto authenticateUser(LoginDto admin);
}
