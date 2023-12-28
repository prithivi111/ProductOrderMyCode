package com.suraj.model;

public class Product {
	int productID;
	String productName;
	String productDescription;
	Double productPrice;
	int productAvailableQuantity;
	
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	public int getProductAvailableQuantity() {
		return productAvailableQuantity;
	}
	public void setProductAvailableQuantity(int productAvailableQuantity) {
		this.productAvailableQuantity = productAvailableQuantity;
	}
	
	
}
