package com.nagarro.productmanagement.productManagement.dao;

import com.nagarro.productmanagement.productManagement.dto.AdminDto;
import com.nagarro.productmanagement.productManagement.dto.ResponseDto;

public interface AdminDao {
	public ResponseDto authenticateAdmin(AdminDto admin);
}
