package com.nagarro.productmanagement.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.nagarro.productmanagement.constants.HQLQueries;
import com.nagarro.productmanagement.dao.AdminDao;
import com.nagarro.productmanagement.dto.LoginDto;
import com.nagarro.productmanagement.dto.ResponseData;
import com.nagarro.productmanagement.dto.ResponseDto;
import com.nagarro.productmanagement.utils.HibernateUtils;

@Component
public class AdminDaoImpl implements AdminDao {

		private Session session;
	
	public AdminDaoImpl(){
		
		this.session=HibernateUtils.createSession();
	}
	
	@Override
	public ResponseDto authenticateAdmin(LoginDto admin) {

		Query query = this.session.createQuery(HQLQueries.GET_ADMIN);
		query.setParameter("username", admin.getUsername());
		query.setParameter("password", admin.getPassword());

		List<Object[]> list= query.list();
		
		ResponseDto response=new ResponseDto();
		
		if(list.size()>0){
		ResponseData adminResponse=new ResponseData();
		adminResponse.setId(Integer.parseInt(""+list.get(0)[0]));
		adminResponse.setUsername(list.get(0)[1].toString());
		
		
		
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
