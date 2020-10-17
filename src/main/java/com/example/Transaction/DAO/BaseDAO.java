package com.example.Transaction.DAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseDAO<T extends Serializable> {
	
	public Class<T> clazz;
	public String tableName;
	
	public final void setClazz(final Class<T> clazzToSet,String tableName) {
		this.clazz = clazzToSet;
		this.tableName = tableName;
	}
	
	@PersistenceContext
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	public T getById(int id){
		
		StringBuilder sb = new StringBuilder("SELECT * FROM  ")
				.append(tableName )
				.append(" WHERE id =:id ");
		List<T> list= manager.createNativeQuery(sb.toString(),clazz).setParameter("id", id)
				.getResultList();
		
		if (list.size() == 0) {
			return null;
		} else {
			return (T) list.get(0);
		}
	}
	
	public List<?> getAll() {
		StringBuilder sb = new StringBuilder("SELECT * FROM  ")
				.append(tableName );
		List<?> list= manager.createNativeQuery(sb.toString(),clazz)
				.getResultList();
		
		if (list.size() == 0) {
			return new ArrayList<T>();
		} else {
			return list;
		}
	}
	
	public List<?> getPagination(int page, int limit) {
		StringBuilder sb = new StringBuilder("SELECT * FROM  ")
				.append(tableName )
				.append(" ORDER BY created_at desc" )
				.append(" OFFSET :page LIMIT :limit");
		List<?> list= manager.createNativeQuery(sb.toString(),clazz)
				.setParameter("page", (page-1)*limit)
				.setParameter("limit", limit)
				.getResultList();
		
		if (list.size() == 0) {
			return new ArrayList<T>();
		} else {
			return list;
		}
	}
	
	public Integer getCount() {
		return getAll().size();
	}
	
	public T add(T entity) {
		manager.persist(entity);
		return entity;
	}
	
	public T edit(T entity) {
		manager.merge(entity);
		return entity;
	}
	
	public void delete (Integer id) {
		final T entity = getById(id);
		manager.remove(entity);
	}

}
