package com.nagarro.productmanagement.service.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.productmanagement.constants.HQLQueries;
import com.nagarro.productmanagement.dao.AdminDao;
import com.nagarro.productmanagement.dto.LoginDto;
import com.nagarro.productmanagement.dto.ResponseDto;
import com.nagarro.productmanagement.models.Admin;
import com.nagarro.productmanagement.service.AdminService;
import com.nagarro.productmanagement.utils.HibernateUtils;


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
