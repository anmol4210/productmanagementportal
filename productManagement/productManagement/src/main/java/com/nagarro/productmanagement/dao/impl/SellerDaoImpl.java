package com.nagarro.productmanagement.dao.impl;

import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.nagarro.productmanagement.constants.Constants;
import com.nagarro.productmanagement.constants.HQLQueries;
import com.nagarro.productmanagement.dao.SellerDao;
import com.nagarro.productmanagement.dto.LoginDto;
import com.nagarro.productmanagement.dto.Response;
import com.nagarro.productmanagement.dto.ResponseData;
import com.nagarro.productmanagement.dto.ResponseDto;
import com.nagarro.productmanagement.dto.SellerDetailsDto;
import com.nagarro.productmanagement.dto.SellerRegistrationDto;
import com.nagarro.productmanagement.dto.StatusDto;
import com.nagarro.productmanagement.models.Seller;
import com.nagarro.productmanagement.models.SellerDetails;
import com.nagarro.productmanagement.utils.HibernateUtils;

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
			response.setMessage("Your registration is not yet approved");
			}

		else if(list.get(0)[2].toString().equals(Constants.REJECTED)){
			response.setStatus(403);
			response.setData(adminResponse);
			response.setMessage("Registration has been cancelled");
			}
		}
		else{
			response.setStatus(401);
			response.setMessage("Invalid username or password");
		}
		return response;
	}

	

	@Override
	public ResponseDto updateSellerStatus(List<StatusDto> statusDto) {
		ResponseDto response=new ResponseDto();
		System.out.println("status service dao called");
		try {
			for(StatusDto status:statusDto) {
				System.out.println("status :"+status.getStatus());
				Object object = session.load(Seller.class,new Integer(""+status.getId()));
				Seller seller=(Seller) object;
				seller.setSellerstatus(status.getStatus());
				
		}
			session.getTransaction().commit();
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return response;
	}

	@Override
	public Response getAllSellers(String sortBy, List<String> status, String searchKeyword, String searchType) {
	System.out.println("getting all sellers");
	
		Response<List<SellerDetailsDto>> response=new Response();
		try {
	
			
            String whereClause = "";
            String sortOrder =" ORDER BY FIELD(sellerDetails.seller.sellerstatus, 'NEED_APPROVAL','APPROVED','REJECTED')";
            
            if(sortBy!=null) {
    //        	System.out.println("status not null"+status);
                   //whereClause = " WHERE sellerDetails.seller.sellerstatus = '" + status + "'"; 
            		sortOrder=" ORDER BY sellerDetails.seller."+sortBy;
            }  
            
            if(status!=null) {
  //          	System.out.println("sort by not null:"+sortBy);
            	whereClause = " WHERE sellerDetails.seller.sellerstatus IN (";
                int count=0;   
            	for(String column: status) {
            		if(count!=0) {
            			whereClause +=",";
            		}
                	   whereClause +="'"+ column+"'";
                	   count++;
                   }
            	whereClause +=")";
            }
            
            if(!Objects.isNull(searchKeyword) && !Objects.isNull(searchType)) {
                sortOrder = "";
                whereClause = " WHERE sellerDetails."+searchType+" LIKE '%"+searchKeyword+"%'";
         }


			
			Query query = this.session.createQuery("FROM SellerDetails as sellerDetails "+whereClause + sortOrder);
	
			
		List<SellerDetails> sellerList= query.list();

//		System.out.println("seller list size from database"+sellerList.size());
		
		List<SellerDetailsDto> responseList=new ArrayList();
		
		for(SellerDetails sellerDetails : sellerList) {
			
			SellerDetailsDto sellerResponseDto=new SellerDetailsDto();
			
			sellerResponseDto.setStatus(sellerDetails.getSeller().getSellerstatus());
			sellerResponseDto.setCompanyname(sellerDetails.getCompanyname());
			sellerResponseDto.setAddress(sellerDetails.getAddress());
			sellerResponseDto.setEmail(sellerDetails.getEmail());
			sellerResponseDto.setGst(sellerDetails.getGst());
			sellerResponseDto.setOwnername(sellerDetails.getOwnername());
			sellerResponseDto.setTelephone(sellerDetails.getTelephone());
			sellerResponseDto.setUsername(sellerDetails.getSeller().getSellername());
			sellerResponseDto.setSellerid(""+sellerDetails.getSeller().getId());
		
			responseList.add(sellerResponseDto);
		
		}
		
		System.out.println("list size:"+responseList.size());
		response.setStatus(200);
		response.setData(responseList);
		
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return response;
	}

	@Override
	public Response getSeller(String id) {
		Response<SellerDetailsDto> response=new Response();
		
		try {
		int sellerid=Integer.parseInt(id);
		Query query = this.session.createQuery("FROM SellerDetails as sellerdetails where sellerdetails.seller.id=:id");
		query.setParameter("id", sellerid);
	
		List<SellerDetails> sellerList= query.list();
		System.out.println(sellerList.size());
	if(sellerList.size()>0) {
		SellerDetailsDto sellerResponseDto=new SellerDetailsDto();	
		SellerDetails sellerDetails=sellerList.get(0);
		sellerResponseDto.setStatus(sellerDetails.getSeller().getSellerstatus());
		sellerResponseDto.setCompanyname(sellerDetails.getCompanyname());
		sellerResponseDto.setAddress(sellerDetails.getAddress());
		sellerResponseDto.setEmail(sellerDetails.getEmail());
		sellerResponseDto.setGst(sellerDetails.getGst());
		sellerResponseDto.setOwnername(sellerDetails.getOwnername());
		sellerResponseDto.setTelephone(sellerDetails.getTelephone());
		sellerResponseDto.setUsername(sellerDetails.getSeller().getSellername());
		response.setStatus(200);
		response.setData(sellerResponseDto);
	}
	else {
		response.setStatus(404);
		response.setMessage("No Data Found");
		}
	}catch(Exception e) {}
		return response;
	}

	@Override
	public Response updateSeller(SellerDetailsDto sellerDetailsDto, String id) {
	Response<SellerDetails> response=new Response();
		
		try {
		
			int sellerid=Integer.parseInt(id);
			Query query = this.session.createQuery("FROM SellerDetails as sellerdetails where sellerdetails.seller.id=:id");
			query.setParameter("id", sellerid);
		
			
			List<SellerDetails> sellerList= query.list();
		
			if(sellerList.size()>0) {
				
		Object object = session.load(SellerDetails.class,new Integer(""+sellerList.get(0).getId()));
		SellerDetails sellerDetails=(SellerDetails) object;
		
	
		
		sellerDetails.setCompanyname(sellerDetailsDto.getCompanyname());
		sellerDetails.setTelephone(sellerDetailsDto.getTelephone());
		sellerDetails.setAddress(sellerDetailsDto.getAddress());
		sellerDetails.setOwnername(sellerDetailsDto.getOwnername());
		sellerDetails.setEmail(sellerDetailsDto.getEmail());
		sellerDetails.setGst(sellerDetailsDto.getGst());
		
		session.getTransaction().commit();
			response.setStatus(200);
			response.setData(sellerDetails);
			
			}
		}catch(Exception e) {
			response.setStatus(400);
			response.setMessage(e.getMessage());
			
		}
	
		return response;
	}
		
	

}
