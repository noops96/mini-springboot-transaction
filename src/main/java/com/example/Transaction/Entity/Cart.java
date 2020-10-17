package com.example.Transaction.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "buyer_id")
	private Integer buyerId;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "cart", fetch = FetchType.LAZY)
	private List<CartItem> cartItem;

	public Integer getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}

	public List<CartItem> getCartItem() {
		return cartItem;
	}

	public void setCartItem(List<CartItem> cartItem) {
		this.cartItem = cartItem;
	}
}