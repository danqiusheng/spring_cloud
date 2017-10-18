package com.moa.datasource.service;


import com.moa.datasource.model.customer.Customer;
import com.moa.datasource.model.order.Order;

public interface StoreService {
	
	void store(Customer customer, Order order) throws Exception;
	
	void storeWithStoreException(Customer customer, Order order) throws Exception;
	void storeWithNoRollbackException(Customer customer, Order order) throws Exception;

}
