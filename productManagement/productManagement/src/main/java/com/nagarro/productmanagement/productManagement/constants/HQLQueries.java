package com.nagarro.productmanagement.productManagement.constants;

public class HQLQueries {
	private static final String ADMIN_TABLE="com.nagarro.productmanagement.productManagement.models.Admin";
	private static final String SELLER_TABLE="com.nagarro.productmanagement.productManagement.models.Seller";
	
	public static final String GET_ADMIN="SELECT admin.id , admin.username FROM "+ADMIN_TABLE+" as admin where admin.username=:username "
													+ "and admin.password=:password";

	

	public static final String GET_SELLER="SELECT seller.id , seller.sellername, seller.sellerstatus FROM "+SELLER_TABLE+" as seller"
			+ "								 where seller.sellername=:username "
													+ "and seller.sellerpassword=:password order by FIELD (seller.status,'NEED_APPROVAL','APPROVED','REJECTED')";

}
