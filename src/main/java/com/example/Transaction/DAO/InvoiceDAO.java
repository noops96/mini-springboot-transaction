package com.example.Transaction.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.Transaction.Entity.Invoice;
@Transactional 
@Repository
public class InvoiceDAO extends BaseDAO<Invoice> {
	
	@PersistenceContext
	private EntityManager manager;
	 
	public InvoiceDAO(){
		setClazz(Invoice.class,"invoice");
	}
	
	@SuppressWarnings("unchecked")
	public List<Invoice> getByBuyerId(int id){
		
		StringBuilder sb = new StringBuilder("SELECT * FROM  ")
				.append("invoice" )
				.append(" WHERE buyer_id =:id ");
		List<Invoice> list= manager.createNativeQuery(sb.toString(),Invoice.class).setParameter("id", id)
				.getResultList();
		
		return list;
	}

}
