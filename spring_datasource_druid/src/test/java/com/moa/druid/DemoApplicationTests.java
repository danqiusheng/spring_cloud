package com.moa.druid;

import com.moa.druid.primary.model.User;
import com.moa.druid.primary.repository.UserRepository;
import com.moa.druid.secondary.repository.MessageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {


	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private UserRepository userRepository;

	@Test
	@Transactional
	@Rollback(false)
	public void contextLoads() {
		userRepository.save(new User("张三","Hello atomikos",1));
		userRepository.save(new User("李四","Hello atomikos",6));
		userRepository.save(new User("王武","Hello atomikos",4));

		//messageRepository.save(new User("张三","Hello atomikos",1));
		//messageRepository.save(new User("李四","Hello atomikos",6));
		//messageRepository.save(new User("王武","Hello atomikos",4));

		messageRepository.save(new User("张三","Hello atomikos",1));
		messageRepository.save(new User("李四","Hello atomikos",6));
		messageRepository.save(new User("王武","Hello atomikos",4));

	//	System.out.println(1/0);

	/*	 messageRepository.save(new User("1","Hello","hello everyone"));
	 	messageRepository.save(new User("2","Hello1","hello3  everyone"));
		 messageRepository.save(new User("3","Hello2","hello 33everyone"));*/
	}


}
