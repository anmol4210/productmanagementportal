package com.nagarro.productmanagement.dto;

import java.time.LocalDateTime;



public class NewProductDto {
	private int id;
	private String sellerproductcode;
	private String productname;
	private String shortdiscription;
	private String longdiscription;
	private String dimensions;
	private Float mrp;
	private Float ssp;
	private Float ymp;
	private String primaryimage;
	private String usageinstructins;
	private String status;
	private LocalDateTime createdat;
	private LocalDateTime updatedat;
	private String comments;	
	private String productattributes;
	private String[] galleryImages;
	private String[] categories;
	private int sellerId;
	
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
	public LocalDateTime getCreatedat() {
		return createdat;
	}
	public void setCreatedat(LocalDateTime createdat) {
		this.createdat = createdat;
	}
	public LocalDateTime getUpdatedat() {
		return updatedat;
	}
	public void setUpdatedat(LocalDateTime updatedat) {
		this.updatedat = updatedat;
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
	
	public String[] getGalleryImages() {
		return galleryImages;
	}
	public void setGalleryImages(String[] galleryImages) {
		this.galleryImages = galleryImages;
	}
	public String[] getCategories() {
		return categories;
	}
	public void setCategories(String[] categories) {
		this.categories = categories;
	}
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	
}
