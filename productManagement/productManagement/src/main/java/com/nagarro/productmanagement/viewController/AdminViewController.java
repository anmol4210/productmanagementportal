package com.nagarro.productmanagement.viewController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.productmanagement.dto.LoginDto;
import com.nagarro.productmanagement.dto.ResponseDto;
import com.nagarro.productmanagement.dto.SellerDetailsDto;
import com.nagarro.productmanagement.service.AdminService;

@Controller
//@SessionAttributes("id")
public class AdminViewController {
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value="/admin",method = RequestMethod.POST)  
	public ModelAndView authenticateAdmin(@ModelAttribute() LoginDto user,HttpServletRequest request) {
		ResponseDto responseDto=adminService.authenticateUser(user);
		if(responseDto.getStatus()==200) {
			return new ModelAndView("redirect:/home");  
		}
		else {
			 HttpSession session=request.getSession(true);
			    session.setAttribute("isValid", "false");
			    return new ModelAndView("redirect:/login");  
		}
	}
	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String login() {
		return "index";
	}
	
	
	 
	
	
}
