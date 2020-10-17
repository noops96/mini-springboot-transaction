package com.example.Transaction.Model;

import java.util.List;

import com.example.Transaction.Entity.CartItem;


public class CartItemDTO{
	
	List<CartItem> cartItemList;
	Integer buyerId;
	public List<CartItem> getCartItemList() {
		return cartItemList;
	}
	public void setCartItemList(List<CartItem> cartItemList) {
		this.cartItemList = cartItemList;
	}
	public Integer getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}
	
}