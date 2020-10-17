package com.example.Transaction.Entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "invoice")
public class Invoice extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "inv", fetch = FetchType.LAZY)
	private List<InvoiceDetail> invDet;
	
	@Column(name = "buyer_id")
	private Integer buyerId;
	
	@Column(name = "buyer_name")
	private String 	buyerName;
	
	@Column(name = "prcs_date")
	private Timestamp prcsDate;
	
	@Column(name = "tot_price")
	private BigDecimal totPrice;

	public List<InvoiceDetail> getInvDet() {
		return invDet;
	}

	public void setInvDet(List<InvoiceDetail> invDet) {
		this.invDet = invDet;
	}

	public Integer getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public Timestamp getPrcsDate() {
		return prcsDate;
	}

	public void setPrcsDate(Timestamp prcsDate) {
		this.prcsDate = prcsDate;
	}

	public BigDecimal getTotPrice() {
		return totPrice;
	}

	public void setTotPrice(BigDecimal totPrice) {
		this.totPrice = totPrice;
	}

}