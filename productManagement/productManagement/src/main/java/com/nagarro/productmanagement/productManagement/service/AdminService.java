package com.nagarro.productmanagement.productManagement.service;

import com.nagarro.productmanagement.productManagement.dto.AdminDto;
import com.nagarro.productmanagement.productManagement.dto.ResponseDto;
import com.nagarro.productmanagement.productManagement.models.Admin;

public interface AdminService {

	public ResponseDto authenticateUser(AdminDto admin);
}
