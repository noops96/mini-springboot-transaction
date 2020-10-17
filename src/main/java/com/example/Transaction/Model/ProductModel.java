package com.example.Transaction.Model;

import java.math.BigDecimal;

public class ProductModel{
	
	Integer id;
	Integer sellerId;
	BigDecimal price;
	String name;
	ProductTypeModel productType;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSellerId() {
		return sellerId;
	}
	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ProductTypeModel getProductType() {
		return productType;
	}
	public void setProductType(ProductTypeModel productType) {
		this.productType = productType;
	}
	
}