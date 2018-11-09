package com.nagarro.productmanagement.models;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="productdetails")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id",unique=true,nullable=false)
	private int id;
	
	@Column(name="sellerproductcode")
	private String sellerproductcode;
	
	@Column(name="productname")
	private String productname;
	
	@Column(name="shortdiscription")
	private String shortdiscription;
	
	@Column(name="longdescription")
	private String longdiscription;
	
	@Column(name="dimensions")
	private String dimensions;
	
	@Column(name="mrp")
	private Float mrp;
	
	@Column(name="ssp")
	private Float ssp;
	
	@Column(name="ymp")
	private Float ymp;
	
	@Column(name="primaryimage")
	private String primaryimage;
	
	@Column(name="usageinstructions")
	private String usageinstructins;
	
	@Column(name="status")
	private String status;
	
	@Column(name="createdat")
	private String createdat;
	
	@Column(name="updatedat")
	private String updatedat;
	
	@ManyToOne(optional=false)
    @JoinColumn(name="sellerid")
	private Seller seller;

	@Column(name="comments")
	private String comments;
	
	
	@Column(name = "productattributes")
	private String productattributes;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getSellerproductcode() {
		return sellerproductcode;
	}


	public void setSellerproductcode(String sellerproductcode) {
		this.sellerproductcode = sellerproductcode;
	}


	public String getProductname() {
		return productname;
	}


	public void setProductname(String productname) {
		this.productname = productname;
	}


	public String getShortdiscription() {
		return shortdiscription;
	}


	public void setShortdiscription(String shortdiscription) {
		this.shortdiscription = shortdiscription;
	}


	public String getLongdiscription() {
		return longdiscription;
	}


	public void setLongdiscription(String longdiscription) {
		this.longdiscription = longdiscription;
	}


	public String getDimensions() {
		return dimensions;
	}


	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}


	public Float getMrp() {
		return mrp;
	}


	public void setMrp(Float mrp) {
		this.mrp = mrp;
	}


	public Float getSsp() {
		return ssp;
	}


	public void setSsp(Float ssp) {
		this.ssp = ssp;
	}


	public Float getYmp() {
		return ymp;
	}


	public void setYmp(Float ymp) {
		this.ymp = ymp;
	}


	public String getPrimaryimage() {
		return primaryimage;
	}


	public void setPrimaryimage(String primaryimage) {
		this.primaryimage = primaryimage;
	}


	public String getUsageinstructins() {
		return usageinstructins;
	}


	public void setUsageinstructins(String usageinstructins) {
		this.usageinstructins = usageinstructins;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


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


	public Seller getSeller() {
		return seller;
	}


	public void setSeller(Seller seller) {
		this.seller = seller;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public String getProductattributes() {
		return productattributes;
	}


	public void setProductattributes(String productattributes) {
		this.productattributes = productattributes;
	}
	
}
