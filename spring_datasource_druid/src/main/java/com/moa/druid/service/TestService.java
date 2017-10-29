package com.moa.druid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moa.druid.primary.model.MessageInfo;
import com.moa.druid.primary.model.User;
import com.moa.druid.primary.repository.UserRepository;
import com.moa.druid.secondary.repository.MessageRepository;
import com.moa.druid.secondary.repository.OtherRepository;

@Service
public class TestService {

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OtherRepository other;

	@Transactional
	public void test() {
		userRepository.save(new User("张三", "Hello atomikos", 1));
		userRepository.save(new User("李四", "Hello atomikos", 6));
		userRepository.save(new User("王武", "Hello atomikos", 4));

		messageRepository.save(new MessageInfo("张三", "Hello atomikos", "1"));
		messageRepository.save(new MessageInfo("李四", "Hello atomikos", "6"));
		messageRepository.save(new MessageInfo("王武", "Hello atomikos", "4"));

		other.save(new User("张三", "Hello atomikos", 1));
		other.save(new User("李四", "Hello atomikos", 6));
		other.save(new User("王武", "Hello atomikos", 4));

		System.out.println(1 / 0);

	}
}
