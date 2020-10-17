package com.example.Transaction.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.Transaction.Entity.Cart;
@Transactional 
@Repository
public class CartDAO extends BaseDAO<Cart> {
	
	@PersistenceContext
	private EntityManager manager;
	 
	public CartDAO(){
		setClazz(Cart.class,"cart");
	}
	
	@SuppressWarnings("unchecked")
	public Cart getByBuyerId(int id){
		
		StringBuilder sb = new StringBuilder("SELECT * FROM  ")
				.append("cart" )
				.append(" WHERE buyer_id =:id ");
		List<Cart> list= manager.createNativeQuery(sb.toString(),Cart.class).setParameter("id", id)
				.getResultList();
		
		if (list.size() == 0) {
			return null;
		} else {
			return (Cart) list.get(0);
		}
	}
}
