package com.moa.multids;

import com.moa.multids.model.User;
import com.moa.multids.repository.UserRepository;
import com.moa.multids.service.AnotherUserService;
import com.moa.multids.service.TotalService;
import com.moa.multids.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMultidsApplicationTests {


	@Autowired
	private UserService userService;


	@Autowired
	private AnotherUserService anotherUserService;


	@Autowired
	private PlatformTransactionManager transactionManager;

	@Autowired
	private TotalService totalService;

	@Test
	//@MyTransactional(rollbackFor=Exception.class)
	public void contextLoads() {


		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		// explicitly setting the transaction name is something that can only be done programmatically
		def.setName("SomeTxName");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			totalService.test();
		}
		catch (Exception ex) {
			//transactionManager.rollback(status);
			throw ex;
		}
		 transactionManager.commit(status);
		System.out.println(transactionManager.getClass().getName());
	}



}
