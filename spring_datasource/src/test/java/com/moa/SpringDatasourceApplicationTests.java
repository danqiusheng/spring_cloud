package com.moa;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDatasourceApplicationTests {

	private Logger logger = Logger.getLogger(getClass());


	@Autowired
	private DataSource dataSource;

	@Test
	public void testSave() throws Exception {
		System.out.println(dataSource);
	}

}
