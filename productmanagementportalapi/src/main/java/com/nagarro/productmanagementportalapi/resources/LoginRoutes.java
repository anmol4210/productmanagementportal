package com.nagarro.productmanagementportalapi.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.nagarro.productmanagementportalapi.dto.AdminDto;


@Path("login")
public class LoginRoutes {

	@POST
	@Path("admin")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public String loginAdmin(AdminDto admin){
		//System.out.println("login called");
		//System.out.println(admin.getPassword());
		return "hello" ;
	}
	
	
}
