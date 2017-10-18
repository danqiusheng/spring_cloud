package com.moa.datasource;

import com.moa.datasource.model.customer.Customer;
import com.moa.datasource.model.order.Order;
import com.moa.datasource.repository.customer.CustomerRepository;
import com.moa.datasource.repository.order.OrderRepository;
import com.moa.datasource.service.StoreService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringTestDbApplicationTests {
	@Autowired
	private StoreService storeService;


	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Test
	@Transactional
	@Rollback(false)
	public void testStore() throws Exception {
		Customer c = new Customer();
		c.setName("test");
		c.setAge(30);

		Order o = new Order();
		o.setCode(1);
		o.setQuantity(7);


		storeService.store(c, o);

		Assert.assertNotNull(c.getId());
		Assert.assertNotNull(o.getId());

		Assert.assertEquals(2, customerRepository.findAll().size());
		Assert.assertEquals(2, orderRepository.findAll().size());
	}


	@Test
	public void fun(){

	}

	@Test
	public void contextLoads() {
	}


}
