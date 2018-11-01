package com.nagarro.productmanagement.service;

import com.nagarro.productmanagement.dto.LoginDto;
import com.nagarro.productmanagement.dto.ResponseDto;
import com.nagarro.productmanagement.models.Admin;

public interface AdminService {

	public ResponseDto authenticateUser(LoginDto admin);
}
