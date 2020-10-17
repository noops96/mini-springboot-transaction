package com.example.Transaction.Entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "invoice_detail")
public class InvoiceDetail extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@JsonIgnoreProperties(value = {"invDet"})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id", referencedColumnName = "id")
	private Invoice inv;
	
	@Column(name = "seller_id")
	private Integer seller_id;
	
	@Column(name = "seller_name")
	private String 	sellerName;
	
	@Column(name = "product_id")
	private Integer product_id;
	
	@Column(name = "product_name")
	private String productName;

	@Column(name = "qty")
	private Integer qty;
	
	@Column(name = "price")
	private BigDecimal price;

	public Invoice getInv() {
		return inv;
	}

	public void setInv(Invoice inv) {
		this.inv = inv;
	}

	public Integer getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(Integer seller_id) {
		this.seller_id = seller_id;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}