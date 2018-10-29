package com.nagarro.productmanagement.productManagement.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="sellerdetails")
public class SellerDetails {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id",unique=true,nullable=false)
	private int id;
	

	@Column(name="companyname",unique=false,nullable=false)
	private String companyname;

	@Column(name="ownername",unique=false,nullable=false)
	private String ownername;
	
	@Column(name="address",unique=false,nullable=false)
	private String address;
	
	@Column(name="email",unique=false,nullable=false)
	private String email;
	
	@Column(name="telephone",unique=false,nullable=false)
	private String telephone;
	
	@Column(name="gstnumber",unique=false,nullable=false)
	private String gst;
//	
//	@JoinColumn(name = "sellerid", unique = true)
//    @OneToOne(cascade = CascadeType.ALL)
//	private Seller seller;

	@Column(name="sellerid",unique=false,nullable=false)
	private int sellerid;

//	public Seller getSeller() {
//		return seller;
//	}
//
//	public void setSeller(Seller seller) {
//		this.seller = seller;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getOwnername() {
		return ownername;
	}

	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getGst() {
		return gst;
	}

	public void setGst(String gst) {
		this.gst = gst;
	}

	public int getSellerid() {
		return sellerid;
	}

	public void setSellerid(int sellerid) {
		this.sellerid = sellerid;
	}


public SellerDetails(){
	
}





}
