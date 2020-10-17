package com.example.Transaction.DAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.Transaction.Entity.InvoiceDetail;
@Transactional 
@Repository
public class InvoiceDetailDAO extends BaseDAO<InvoiceDetail> {
	
	@PersistenceContext
	private EntityManager manager;
	 
	public InvoiceDetailDAO(){
		setClazz(InvoiceDetail.class,"invoice_detail");
	}

}
