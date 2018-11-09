package com.nagarro.productmanagement.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.nagarro.productmanagement.dao.ProductFilterDao;
import com.nagarro.productmanagement.dto.NewProductDto;
import com.nagarro.productmanagement.dto.Response;


@Component
public class ScheduleTasks {
	 
	@Autowired 
	ProductFilterDao productDao;
	
	    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss ");

	    //secs mins hour date month year
	    @Scheduled(cron = "0 0 0 * * *")
	    public void reportCurrentTime() {
	    	final String fromEmail = "anmolnarang4210@gmail.com"; //requires valid gmail id
			final String password = "hxhbvqolzrvqtnoj"; // correct password for gmail id
			final String toEmail = "anmolnarang4210@gmail.com"; // can be any email id 
	
			List<String> status=new ArrayList<String>(); 
			status.add("NEED_APPROVAL");
			status.add("REVIEW");
			Response<List<NewProductDto>> response=productDao.getAllProduct(null, status, null, null);
			List<NewProductDto> productList=response.getData();
			
			StringBuffer body=new StringBuffer();
	    	
			for(NewProductDto product:productList) {
				body.append(product.getProductname());
			}
			
	    	SendMail sendmail=new SendMail();
	    	sendmail.sendMail(fromEmail,password,toEmail,"Products Approval Pending",body.toString());
	        System.out.println("The time is now:"+ dateFormat.format(new Date()));
	    }
}
