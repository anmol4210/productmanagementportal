package com.nagarro.productmanagement.productManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.productmanagement.productManagement.dto.AdminDto;
import com.nagarro.productmanagement.productManagement.dto.ResponseDto;
import com.nagarro.productmanagement.productManagement.models.Admin;
import com.nagarro.productmanagement.productManagement.service.AdminService;
import com.nagarro.productmanagement.productManagement.service.impl.AdminServiceImpl;

@RestController
public class AdminController {

	
	@Autowired
	private AdminService adminService;
	

	@GetMapping("/login/admin")
	public String retrieveCoursesForStudent(@PathVariable String studentId) {
		return "";
	}
	
	@PostMapping("/login/admin")
	public ResponseDto loginAdmin(@RequestBody AdminDto admin) {
		return adminService.authenticateUser(admin);
		
	}
}

