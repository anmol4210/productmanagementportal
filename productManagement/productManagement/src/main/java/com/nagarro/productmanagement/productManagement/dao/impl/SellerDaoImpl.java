package com.nagarro.productmanagement.productManagement.dao.impl;

import org.hibernate.Session;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.nagarro.productmanagement.productManagement.constants.Constants;
import com.nagarro.productmanagement.productManagement.constants.HQLQueries;
import com.nagarro.productmanagement.productManagement.dao.SellerDao;
import com.nagarro.productmanagement.productManagement.dto.LoginDto;
import com.nagarro.productmanagement.productManagement.dto.ResponseData;
import com.nagarro.productmanagement.productManagement.dto.ResponseDto;
import com.nagarro.productmanagement.productManagement.dto.SellerRegistrationDto;
import com.nagarro.productmanagement.productManagement.models.Seller;
import com.nagarro.productmanagement.productManagement.models.SellerDetails;
import com.nagarro.productmanagement.productManagement.utils.HibernateUtils;

@Component
public class SellerDaoImpl implements SellerDao {

	private Session session;

	public SellerDaoImpl() {

		this.session = HibernateUtils.createSession();
	}

	@Override
	public ResponseDto registerSeller(SellerRegistrationDto sellerRegistrationDto) {

		ResponseDto responseDto = new ResponseDto();

		if (sellerRegistrationDto.getPassword().equals(sellerRegistrationDto.getConfirmPassword())) {
			session.beginTransaction();

			Seller seller = new Seller();
			seller.setSellername(sellerRegistrationDto.getUsername());
			seller.setSellerpassword(sellerRegistrationDto.getPassword());
			seller.setSellerstatus(Constants.NEED_APPROVAL);

			int sellerid = Integer.parseInt(session.save(seller).toString());

			SellerDetails sellerDetails = new SellerDetails();
			sellerDetails.setCompanyname(sellerRegistrationDto.getCompanyName());
			sellerDetails.setOwnername(sellerRegistrationDto.getOwnerName());
			sellerDetails.setAddress(sellerRegistrationDto.getAddress());
			sellerDetails.setEmail(sellerRegistrationDto.getEmail());
			sellerDetails.setGst(sellerRegistrationDto.getGst());
			sellerDetails.setTelephone(sellerRegistrationDto.getTelephone());
			sellerDetails.setSellerid(sellerid);

			if (session.save(sellerDetails) != null) {
				ResponseData responseData = new ResponseData();
				responseData.setId(sellerid);
				responseData.setUsername(seller.getSellername().toString());
				responseData.setToken("");
				responseDto.setStatus(200);
				responseDto.setData(responseData);

				session.getTransaction().commit();

			}
		} else {
			ResponseData responseData = new ResponseData();

			responseDto.setStatus(403);
			responseDto.setData(responseData);
			responseDto.setMessage("password mismatch");

		}

		return responseDto;

	}

	@Override
	public ResponseDto authenticateSeller(LoginDto sellerLoginDto) {
		Query query = this.session.createQuery(HQLQueries.GET_SELLER);
		query.setParameter("username", sellerLoginDto.getUsername());
		query.setParameter("password", sellerLoginDto.getPassword());

		List<Object[]> list= query.list();

		ResponseDto response=new ResponseDto();
		
		if(list.size()>0){
		ResponseData adminResponse=new ResponseData();
		adminResponse.setId(Integer.parseInt(""+list.get(0)[0]));
		adminResponse.setUsername(list.get(0)[1].toString());
		System.out.println("Status:"+list.get(0)[2].toString());
		response.setStatus(200);
		response.setData(adminResponse);
		
		}
		else{
			response.setStatus(401);
			response.setMessage("Invalid username or password");
		}
		return response;
	}

}
