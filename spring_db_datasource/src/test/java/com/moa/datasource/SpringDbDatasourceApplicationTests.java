package com.moa.datasource;

import com.moa.datasource.primary.dao.UserRepository;
import com.moa.datasource.primary.model.User;
import com.moa.datasource.primary.service.UserService;
import com.moa.datasource.secondary.dao.StudentRepository;
import com.moa.datasource.secondary.model.Student;
import com.moa.datasource.secondary.service.StudentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDbDatasourceApplicationTests {


	@Autowired
	private UserRepository userRepository;
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private StudentService stuentService;


	@Autowired
	private UserService userService;

	@Test
	@Transactional(transactionManager = "jtaTransactionManager")
	@Rollback(false)
	public void test() throws Exception {
		userService.save(new User("aaa", 10));
		userService.save(new User("bbb", 20));
		userService.save(new User("ccc", 30));
		userService.save(new User("ddd", 40));
		userService.save(new User("eee", 50));

		 //Assert.assertEquals(5, studentRepository.findAll().size());

		stuentService.save(new Student("o1", 1));
		stuentService.save(new Student("o2", 2));
		stuentService.save(new Student("o3", 3));

//		 Assert.assertEquals(15, studentRepository.findAll().size());
	}
}
