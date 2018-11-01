package com.nagarro.productmanagement.dao;

import com.nagarro.productmanagement.dto.LoginDto;
import com.nagarro.productmanagement.dto.ResponseDto;

public interface AdminDao {
	public ResponseDto authenticateAdmin(LoginDto admin);
}
