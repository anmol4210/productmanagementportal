package com.nagarro.productmanagement.productManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminViewController {
	
	@RequestMapping(value="/admin",method = RequestMethod.POST)  
	public String authenticateAdmin() {
		
		return "admin";
	}
}
