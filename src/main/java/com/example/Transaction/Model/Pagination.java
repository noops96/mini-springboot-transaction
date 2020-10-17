package com.example.Transaction.Model;

import java.util.List;


public class Pagination{
	
	List<?> data;
	Integer count;
	
	public List<?> getData() {
		return data;
	}
	public void setData(List<?> list) {
		this.data = list;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
}