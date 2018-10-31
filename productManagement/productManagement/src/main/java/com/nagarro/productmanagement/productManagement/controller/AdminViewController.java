package com.nagarro.productmanagement.productManagement.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nagarro.productmanagement.productManagement.dto.LoginDto;
import com.nagarro.productmanagement.productManagement.dto.ResponseDto;
import com.nagarro.productmanagement.productManagement.service.AdminService;

@Controller
//@SessionAttributes("id")
public class AdminViewController {
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value="/admin",method = RequestMethod.POST)  
	public String authenticateAdmin(@ModelAttribute() LoginDto user,HttpServletRequest request) {
		//System.out.println(user.getUsername()+" "+user.getPassword());
		ResponseDto responseDto=adminService.authenticateUser(user);
		//System.out.println(responseDto.getStatus());
		if(responseDto.getStatus()==200) {
			return "home";
		}
		else {
			 HttpSession session=request.getSession(true);
			    //session.setAttribute("username", user.getUsername());
			    session.setAttribute("isValid", "false");
			   return "index";
			    //return new ModelAndView("redirect:/index");  
		}
	}
}
