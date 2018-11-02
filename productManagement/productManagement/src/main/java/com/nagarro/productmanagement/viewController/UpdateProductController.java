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

import com.nagarro.productmanagement.dao.ProductFilterDao;
import com.nagarro.productmanagement.dao.impl.ProductDaoImpl;
import com.nagarro.productmanagement.dao.impl.ProductFilterDaoImpl;
import com.nagarro.productmanagement.dto.CommentsDto;
import com.nagarro.productmanagement.dto.NewProductDto;
import com.nagarro.productmanagement.dto.Response;
import com.nagarro.productmanagement.dto.SellerDetailsDto;
import com.nagarro.productmanagement.dto.StatusDto;
import com.nagarro.productmanagement.service.ProductService;

@Controller
public class UpdateProductController {

	@Autowired
	ProductService productService;
	
	@Autowired
	ProductFilterDao productFilter;

	@RequestMapping(value="/allproducts/filterproducts",method=RequestMethod.GET)
	public ModelAndView filterSellers(ModelMap model,
			@RequestParam(value="sortBy",required=false) String sortBy,
			@RequestParam(value="status",required=false) List<String> status,
			 @RequestParam(value="searchBy", required=false) String searchType,
				@RequestParam(value="keyword", required=false) String searchKeyword
			) {
		//ProductFilterDaoImpl productFilter=new ProductFilterDaoImpl();
		//System.out.println("getting all products.........");
		Response<List<NewProductDto>> response=	productFilter.getAllProduct(sortBy,status,searchType,searchKeyword);
		//System.out.println("returned......");
		model.addAttribute("productList", response.getData());
		
		return new ModelAndView("productlist");	
	
		
	}
	
	
	@RequestMapping(value="/product/allproducts",method=RequestMethod.GET)
	public ModelAndView allProducts(ModelMap model) {
		
	System.out.println("getting all products.........");
	Response<List<NewProductDto>> response=	productService.getAllProducts(null,null,null,null);
	System.out.println("returned......");
	System.out.println(response.getData().size());
	model.addAttribute("productList", response.getData());
	
	return new ModelAndView("productlist");
	}
	
	@RequestMapping(value="/product/productdetails/{id}",method=RequestMethod.GET)
	public ModelAndView productDetails(@PathVariable("id") String productid,ModelMap model ) {

		Response<NewProductDto> response=productService.getProduct(productid);
		NewProductDto newProduct=response.getData();
		model.addAttribute("product",newProduct);
		return new ModelAndView("productDetails");
	}
	
	
	@RequestMapping(value="/product/productdetails/updateproduct",method=RequestMethod.POST)
	public ModelAndView updateProductStatus(ModelMap model,@ModelAttribute() CommentsDto comments,HttpServletRequest request) {
		System.out.println("comments:"+comments.getComments());
		try {
			String productid="";
			String status="";
			Cookie[] cookies=request.getCookies();
			if(cookies!=null){
				for(Cookie cookie:cookies){
					if(cookie.getName().equals("productId")){
	    			productid=cookie.getValue().toString();
	    		}
	    		if(cookie.getName().equals("productstatus")){
	    			status=cookie.getValue();
	    		}
	    	}
	    }
			
			List<StatusDto> statusList=new ArrayList();
				StatusDto statusDto=new StatusDto();
				statusDto.setId(productid);
				statusDto.setStatus(status);
				statusDto.setMessage(comments.getComments());
				statusList.add(statusDto);
				
			productService.updateProductStatus(statusList);
			
		}catch(Exception e) {
				System.out.println(e );
			}
		return new ModelAndView("redirect:/product/allproducts");
	}
	
	@RequestMapping(value="/product/changestatus",method=RequestMethod.GET)
	public ModelAndView changeProductsStatus(@RequestParam(value="id",required=false) List<String> id) {
		
		try {
		List<StatusDto> statusList=new ArrayList();
		for(String productId:id ) {
		
			StatusDto statusDto=new StatusDto();
			statusDto.setId(productId);
			statusDto.setStatus("APPROVED");
			statusList.add(statusDto);
		}
		
		productService.updateProductStatus(statusList);
		}catch(Exception e) {
			System.out.println(e );
		}
		
		return new ModelAndView("redirect:/product/allproducts");
	}
}

