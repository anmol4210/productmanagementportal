package com.nagarro.productmanagement.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.nagarro.productmanagement.models.Product;
import com.nagarro.productmanagement.models.SellerDetails;
import com.nagarro.productmanagement.utils.HibernateUtils;

public class EmailSeller {

	private Session session;
	
	public void emailSeller(Product product,String comment) {
		
		this.session = HibernateUtils.createSession();
		this.session.beginTransaction();
		
		Query query = this.session.createQuery("Select sellerdetails.email FROM SellerDetails as sellerdetails where sellerdetails.seller.id= :id ");
			query.setParameter("id",Integer.parseInt(""+product.getSeller().getId()));
			
			
			List<SellerDetails> sellers=query.list();

			final String fromEmail = "anmolnarang4210@gmail.com"; //requires valid gmail id
			final String password = "hxhbvqolzrvqtnoj"; // correct password for gmail id
			final String toEmail = sellers.get(0).getEmail(); // can be any email id 


			SendMail sendmail=new SendMail();
			String message=comment;
	    	sendmail.sendMail(fromEmail,password,toEmail,"Product Status Changed",message);
			
	}
}
