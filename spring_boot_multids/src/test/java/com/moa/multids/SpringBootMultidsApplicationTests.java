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
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMultidsApplicationTests {


	@Autowired
	private UserService userService;


	@Autowired
	private AnotherUserService anotherUserService;


	@Autowired
	private TotalService totalService;

	@Test
	public void contextLoads() {

		 totalService.test();
	}



}
