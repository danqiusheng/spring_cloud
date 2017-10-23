package com.moa.multids.service;

import com.moa.multids.config.TargetDataSource;
import com.moa.multids.model.User;
import com.moa.multids.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/10/23.
 */
@Service
public class UserServiceImpl  implements  UserService{

    @Autowired
    private UserRepository userRepository;
    @Transactional
    @TargetDataSource("ds1")
    public void save(User user) {
        userRepository.save(user);
    }
}
