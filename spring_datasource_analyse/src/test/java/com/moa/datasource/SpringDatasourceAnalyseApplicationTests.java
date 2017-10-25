package com.moa.datasource;

import com.moa.datasource.primary.model.User;
import com.moa.datasource.primary.repository.UserRepository;
import com.moa.datasource.primary.repository.UserService;
import com.moa.datasource.secondary.model.MessageInfo;
import com.moa.datasource.secondary.model.MessageService;
import com.moa.datasource.secondary.repository.MessageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.resources.Messages;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDatasourceAnalyseApplicationTests {

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private UserRepository userRepository;


	@Autowired
	private UserService userService;

	@Autowired
	private MessageService messageService;

	@Test
	@Transactional
	@Rollback(false)
	public void contextLoads() {
		userService.save(new User("张三","Hello atomikos",1));
		userService.save(new User("李四","Hello atomikos",6));
		userService.save(new User("王武","Hello atomikos",4));


		messageService.save(new MessageInfo("1","Hello","hello everyone"));
		messageService.save(new MessageInfo("2","Hello1","hello3  everyone"));
		messageService.save(new MessageInfo("3","Hello2","hello 33everyone"));
	}

}
