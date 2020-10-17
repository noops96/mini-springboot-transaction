package com.example.Transaction.DAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.Transaction.Entity.CartItem;
@Transactional 
@Repository
public class CartItemDAO extends BaseDAO<CartItem> {
	
	@PersistenceContext
	private EntityManager manager;
	 
	public CartItemDAO(){
		setClazz(CartItem.class,"cart_item");
	}

}
