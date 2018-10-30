package com.nagarro.productmanagement.productManagement.dao.impl;

import org.hibernate.Session;

import java.util.ArrayList;
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
import com.nagarro.productmanagement.productManagement.dto.SellerResponseDto;
import com.nagarro.productmanagement.productManagement.dto.StatusDto;
import com.nagarro.productmanagement.productManagement.models.Seller;
import com.nagarro.productmanagement.productManagement.models.SellerDetails;
import com.nagarro.productmanagement.productManagement.utils.HibernateUtils;

@Component
public class SellerDaoImpl implements SellerDao {

	private Session session;

	public SellerDaoImpl() {

		this.session = HibernateUtils.createSession();
		this.session.beginTransaction();
	}

	@Override
	public ResponseDto registerSeller(SellerRegistrationDto sellerRegistrationDto) {

		ResponseDto responseDto = new ResponseDto();

		if (sellerRegistrationDto.getPassword().equals(sellerRegistrationDto.getConfirmPassword())) {
			//session.beginTransaction();

			Seller seller = new Seller();
			seller.setSellername(sellerRegistrationDto.getUsername());
			seller.setSellerpassword(sellerRegistrationDto.getPassword());
			seller.setSellerstatus(Constants.NEED_APPROVAL);

			//int sellerid = Integer.parseInt(session.save(seller).toString());

			SellerDetails sellerDetails = new SellerDetails();
			sellerDetails.setCompanyname(sellerRegistrationDto.getCompanyName());
			sellerDetails.setOwnername(sellerRegistrationDto.getOwnerName());
			sellerDetails.setAddress(sellerRegistrationDto.getAddress());
			sellerDetails.setEmail(sellerRegistrationDto.getEmail());
			sellerDetails.setGst(sellerRegistrationDto.getGst());
			sellerDetails.setTelephone(sellerRegistrationDto.getTelephone());
			sellerDetails.setSeller(seller);
			//sellerDetails.setSellerid(sellerid);

			
			if (session.save(sellerDetails) != null) {
				ResponseData responseData = new ResponseData();
				System.out.println("seller id:"+seller.getId());
				responseData.setId(seller.getId());
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
		
		if(list.get(0)[2].toString().equals(Constants.APPROVED)){
		response.setStatus(200);
		response.setData(adminResponse);
		}
		else if(list.get(0)[2].toString().equals(Constants.NEED_APPROVAL)){
			response.setStatus(403);
			response.setData(adminResponse);
			response.setMessage("Needs Approval");
			}

		else if(list.get(0)[2].toString().equals(Constants.REJECTED)){
			response.setStatus(403);
			response.setData(adminResponse);
			response.setMessage("Rejected");
			}
		}
		else{
			response.setStatus(401);
			response.setMessage("Invalid username or password");
		}
		return response;
	}

	

	@Override
	public ResponseDto updateSellerStatus(StatusDto status) {
		ResponseDto response=new ResponseDto();
		
		try {
		Object object = session.load(Seller.class,new Integer(""+status.getId()));
		Seller seller=(Seller) object;
		seller.setSellerstatus(status.getStatus());
		session.getTransaction().commit();
		}catch(Exception e) {}
		return response;
	}

	@Override
	public List<SellerResponseDto> getAllSellers() {
		Query query = this.session.createQuery("FROM SellerDetails");
		//	query.setParameter("username", sellerLoginDto.getUsername());
	//	query.setParameter("password", sellerLoginDto.getPassword());

		List<SellerDetails> sellerList= query.list();

		List<SellerResponseDto> reponseList=new ArrayList();
		
		for(SellerDetails sellerDetails : sellerList) {
			
			SellerResponseDto sellerResponseDto=new SellerResponseDto();
			
			sellerResponseDto.setStatus(sellerDetails.getSeller().getSellerstatus());
			sellerResponseDto.setCompanyname(sellerDetails.getCompanyname());
			sellerResponseDto.setAddress(sellerDetails.getAddress());
			sellerResponseDto.setEmail(sellerDetails.getEmail());
			sellerResponseDto.setGst(sellerDetails.getGst());
			sellerResponseDto.setOwnername(sellerDetails.getOwnername());
			sellerResponseDto.setTelephone(sellerDetails.getTelephone());
			sellerResponseDto.setUsername(sellerDetails.getSeller().getSellername());
		reponseList.add(sellerResponseDto);
		}
		return reponseList;
	}

	@Override
	public SellerResponseDto getSeller(String id) {
		SellerResponseDto sellerResponseDto=new SellerResponseDto();	
		try {
		int sellerid=Integer.parseInt(id);
		Query query = this.session.createQuery("FROM SellerDetails as sellerdetails where sellerdetails.seller.id=:id");
		query.setParameter("id", sellerid);
	
		List<SellerDetails> sellerList= query.list();
		System.out.println(sellerList.size());
	if(sellerList.size()>0) {
		SellerDetails sellerDetails=sellerList.get(0);
		sellerResponseDto.setStatus(sellerDetails.getSeller().getSellerstatus());
		sellerResponseDto.setCompanyname(sellerDetails.getCompanyname());
		sellerResponseDto.setAddress(sellerDetails.getAddress());
		sellerResponseDto.setEmail(sellerDetails.getEmail());
		sellerResponseDto.setGst(sellerDetails.getGst());
		sellerResponseDto.setOwnername(sellerDetails.getOwnername());
		sellerResponseDto.setTelephone(sellerDetails.getTelephone());
		sellerResponseDto.setUsername(sellerDetails.getSeller().getSellername());
	}
	}catch(Exception e) {}
		return sellerResponseDto;
	}

}
