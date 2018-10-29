package com.nagarro.productmanagement.productManagement.constants;

public class HQLQueries {
	private static final String ADMIN_TABLE="com.nagarro.productmanagement.productManagement.models.Admin";
	
	public static final String GET_ADMIN="SELECT admin.id , admin.username FROM "+ADMIN_TABLE+" as admin where admin.username=:username "
													+ "and admin.password=:password";

}
