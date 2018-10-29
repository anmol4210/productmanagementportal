package com.nagarro.productmanagement.productManagement.service.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.nagarro.productmanagement.productManagement.constants.HQLQueries;
import com.nagarro.productmanagement.productManagement.dao.AdminDao;
import com.nagarro.productmanagement.productManagement.dto.LoginDto;
import com.nagarro.productmanagement.productManagement.dto.ResponseDto;
import com.nagarro.productmanagement.productManagement.models.Admin;
import com.nagarro.productmanagement.productManagement.service.AdminService;
import com.nagarro.productmanagement.productManagement.utils.HibernateUtils;


@Component
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDao adminDao;
	
	public AdminServiceImpl(){
		
	}
	
	@Override
	public ResponseDto authenticateUser(LoginDto admin) {
		return adminDao.authenticateAdmin(admin);

	}
}
