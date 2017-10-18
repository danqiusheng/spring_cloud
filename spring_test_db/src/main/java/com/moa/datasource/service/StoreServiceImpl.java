package com.moa.datasource.service;

import com.moa.datasource.model.customer.Customer;
import com.moa.datasource.model.order.Order;
import com.moa.datasource.repository.customer.CustomerRepository;
import com.moa.datasource.repository.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class StoreServiceImpl implements StoreService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Transactional
	public void store(Customer customer, Order order) {
		customerRepository.save(customer);
		orderRepository.save(order);
	}

	@Transactional(rollbackFor = Exception.class)
	public void storeWithStoreException(Customer customer, Order order) throws Exception {
		customerRepository.save(customer);
		orderRepository.save(order);
		throw new Exception();
	}

	@Transactional(noRollbackFor = Exception.class)
	public void storeWithNoRollbackException(Customer customer, Order order) throws Exception {
		customerRepository.save(customer);
		orderRepository.save(order);
		throw new Exception();
	}

}
