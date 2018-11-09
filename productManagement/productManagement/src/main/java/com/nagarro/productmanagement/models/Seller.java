package com.nagarro.productmanagement.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="seller")
public class Seller {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id",unique=true,nullable=false)
	private int id;
	

	@Column(name="sellername",unique=true,nullable=false)
	private String sellername;
	
	@Column(name="sellerpassword",unique=false,nullable=false)
	private String sellerpassword;
	

	@Column(name="createdat")
	private String createdat;
	
	public String getCreatedat() {
		return createdat;
	}

	public void setCreatedat(String createdat) {
		this.createdat = createdat;
	}

	public String getUpdatedat() {
		return updatedat;
	}

	public void setUpdatedat(String updatedat) {
		this.updatedat = updatedat;
	}

	@Column(name="updatedat")
	private String updatedat;
	
	@Column(name="sellerstatus",unique=false,nullable=false)
	private String sellerstatus;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
	}

	public String getSellerpassword() {
		return sellerpassword;
	}

	public void setSellerpassword(String sellerpassword) {
		this.sellerpassword = sellerpassword;
	}

	public String getSellerstatus() {
		return sellerstatus;
	}

	public void setSellerstatus(String sellerstatus) {
		this.sellerstatus = sellerstatus;
	}


}
