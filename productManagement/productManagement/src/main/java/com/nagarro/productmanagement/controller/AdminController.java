package com.nagarro.productmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.productmanagement.dto.LoginDto;
import com.nagarro.productmanagement.dto.ResponseDto;
import com.nagarro.productmanagement.models.Admin;
import com.nagarro.productmanagement.service.AdminService;
import com.nagarro.productmanagement.service.impl.AdminServiceImpl;

@RestController
public class AdminController {

	
	@Autowired
	private AdminService adminService;
	

	@GetMapping("/login/admin")
	public String retrieveCoursesForStudent(@PathVariable String studentId) {
		return "";
	}
	
	@PostMapping("/login/admin")
	public ResponseDto loginAdmin(@RequestBody LoginDto admin) {
		return adminService.authenticateUser(admin);
		
	}
}

