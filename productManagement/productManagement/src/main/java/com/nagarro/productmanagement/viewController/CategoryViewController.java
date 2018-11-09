package com.nagarro.productmanagement.viewController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.productmanagement.dto.CategoryDto;
import com.nagarro.productmanagement.dto.NewProductDto;
import com.nagarro.productmanagement.dto.Response;
import com.nagarro.productmanagement.service.CategoryService;

@Controller
public class CategoryViewController {
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(value="/category",method=RequestMethod.GET)
	public ModelAndView AllCategories(ModelMap model ) {

		Response<List<String>> response=categoryService.getAllCategories();
		List<String> categoryList=response.getData();
		model.addAttribute("categoryList",categoryList);
		return new ModelAndView("categories");
	}
	
	@RequestMapping(value="/category/{categoryname}",method=RequestMethod.GET)
	public ModelAndView categoryProducts(ModelMap model,@PathVariable("categoryname") String categoryname ) {

		Response<List<CategoryDto>> response=categoryService.getCategoryProducts(categoryname);
		List<CategoryDto> productList=response.getData();
		if(productList!=null&&productList.size()>0) {
			model.addAttribute("show",true);
			model.addAttribute("productList",productList);
		}
		else {
			model.addAttribute("show",false);
			model.addAttribute("category",categoryname);
			
	
		}
	
		return new ModelAndView("categoryWiseProducts");
	}
	
	
	@RequestMapping(value="/category/delete/{categoryname}",method=RequestMethod.GET)
	public ModelAndView deleteCategory(ModelMap model,@PathVariable("categoryname") String categoryname ) {
		
		CategoryDto categoryDto=new CategoryDto();
		categoryDto.setCategory(categoryname);
		System.out.println(categoryDto.getCategory());
		categoryService.deleteCategory(categoryDto);
		
		return new ModelAndView("redirect:/category");
	
	}
	
	@RequestMapping(value="create",method=RequestMethod.GET)
	public ModelAndView addcategory(ModelMap model,@RequestParam("name") String categoryname ) {
	//	System.out.println("............."+categoryname);
CategoryDto categoryDto=new CategoryDto();
categoryDto.setCategory(categoryname);
		categoryService.addCategory(categoryDto);
		return new ModelAndView("redirect:/category");
		
	}

	
	
}
