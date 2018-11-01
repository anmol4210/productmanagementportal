package com.nagarro.productmanagement.viewController;

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
import com.nagarro.productmanagement.service.AdminService;

@Controller
//@SessionAttributes("id")
public class AdminViewController {
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value="/admin",method = RequestMethod.POST)  
	public ModelAndView authenticateAdmin(@ModelAttribute() LoginDto user,HttpServletRequest request) {
		//System.out.println(user.getUsername()+" "+user.getPassword());
		ResponseDto responseDto=adminService.authenticateUser(user);
		//System.out.println(responseDto.getStatus());
		if(responseDto.getStatus()==200) {
			return new ModelAndView("redirect:/home");  
			//	return "home";
		}
		else {
			 HttpSession session=request.getSession(true);
			    //session.setAttribute("username", user.getUsername());
			    session.setAttribute("isValid", "false");
			    return new ModelAndView("redirect:/login");  
			    //return "index";
			    //return new ModelAndView("redirect:/index");  
		}
	}
	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String login() {
	//	System.out.println("home called");
		return "index";
	}
	
	@RequestMapping(value="/home",method = RequestMethod.GET)
	public String home() {
		System.out.println("home called");
		return "home";
	}
}
