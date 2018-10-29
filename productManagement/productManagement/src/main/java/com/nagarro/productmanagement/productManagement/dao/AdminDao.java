package com.nagarro.productmanagement.productManagement.dao;

import com.nagarro.productmanagement.productManagement.dto.LoginDto;
import com.nagarro.productmanagement.productManagement.dto.ResponseDto;

public interface AdminDao {
	public ResponseDto authenticateAdmin(LoginDto admin);
}
