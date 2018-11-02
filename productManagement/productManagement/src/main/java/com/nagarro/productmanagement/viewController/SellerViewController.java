package com.nagarro.productmanagement.viewController;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.productmanagement.dto.CommentsDto;
import com.nagarro.productmanagement.dto.LoginDto;
import com.nagarro.productmanagement.dto.NewProductDto;
import com.nagarro.productmanagement.dto.Response;
import com.nagarro.productmanagement.dto.SellerDetailsDto;
import com.nagarro.productmanagement.dto.StatusDto;
import com.nagarro.productmanagement.service.AdminService;
import com.nagarro.productmanagement.service.ProductService;
import com.nagarro.productmanagement.service.SellerService;

@Controller
public class SellerViewController {

	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private ProductService productService;
	
	
	@RequestMapping(value="/home",method = RequestMethod.GET)
	public String home(ModelMap model) {
		
		Response<List<SellerDetailsDto>> response=sellerService.getAllSellers(null, null,null,null);
		
		model.addAttribute("sellerList",response.getData());
		return "home";
		
	}
	
	@RequestMapping(value="/seller/details/update",method = RequestMethod.POST)
	public ModelAndView update(ModelMap model,HttpServletRequest request) {
		String sellerid="";
		String status="";
		
		Cookie[] cookies=request.getCookies();
	    if(cookies!=null){
	    	for(Cookie cookie:cookies){
	    		if(cookie.getName().equals("sellerId")){
	    			sellerid=cookie.getValue().toString();
	    		}
	    		if(cookie.getName().equals("status")){
	    			status=cookie.getValue();
	    		}
	    	}
	    }
		
		List<StatusDto> statusList=new ArrayList();
		StatusDto statusDto=new StatusDto();
		statusDto.setId(sellerid);
		statusDto.setStatus(status);

	statusList.add(statusDto);
		sellerService.updateSellerStatus(statusList);
		
		return new ModelAndView("redirect:/home");
		
		
	}
	
	@RequestMapping(value="/seller/search",method=RequestMethod.GET)
	public ModelAndView filterSellers(ModelMap model,
			@RequestParam(value="sortBy",required=false) String sortBy,
			@RequestParam(value="status",required=false) List<String> status,
			 @RequestParam(value="searchBy", required=false) String searchType,
				@RequestParam(value="keyword", required=false) String searchKeyword
			) {
	
		Response<List<SellerDetailsDto>> response=sellerService.getAllSellers(sortBy, status,searchKeyword,searchType);
		
		model.addAttribute("sellerList",response.getData());
		return new ModelAndView("home","command",new SellerDetailsDto() );
	}
	
	@RequestMapping(value="/seller/details/{id}",method=RequestMethod.GET)
	public ModelAndView sellerDetails(@PathVariable("id") String id,ModelMap model) {
		System.out.println("id:"+id);
		Response<SellerDetailsDto> response=sellerService.getSeller(id);
		SellerDetailsDto seller=response.getData();
		seller.setSellerid(id);
		model.addAttribute("sellerdetails",seller);
		
		return new ModelAndView("sellerDetails","command",new SellerDetailsDto());
	}
	
	@RequestMapping(value="/seller/details/products",method = RequestMethod.GET)
	public ModelAndView getProducts(ModelMap model,HttpServletRequest request) {
		String sellerid="";
		Cookie[] cookies=request.getCookies();
	    if(cookies!=null){
	    	for(Cookie cookie:cookies){
	    		if(cookie.getName().equals("sellerId")){
	    			sellerid=cookie.getValue();
	    		}
	    	}
	    }
	    
		Response<List<NewProductDto>> response=productService.getProducts(sellerid);
		model.addAttribute("productList",response.getData());
		return new ModelAndView("product","command",new NewProductDto());
	}
	

	@RequestMapping(value="/seller/updatestatusall",method = RequestMethod.GET)
	public ModelAndView updateAllSeller(@RequestParam(value="id",required=false) List<String> id) {
		List<StatusDto> statusList=new ArrayList();
		for(String sellerid:id) {
		StatusDto statusDto=new StatusDto();
		
		statusDto.setId(sellerid);
		statusDto.setStatus("APPROVED");
		statusList.add(statusDto);
		}
		sellerService.updateSellerStatus(statusList);
		
		return new ModelAndView("redirect:/home");	
		
	} 
	
}
