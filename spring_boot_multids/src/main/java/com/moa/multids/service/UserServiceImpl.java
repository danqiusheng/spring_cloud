package com.moa.multids.service;

import com.moa.multids.config.MyTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moa.multids.config.TargetDataSource;
import com.moa.multids.model.User;
import com.moa.multids.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/10/23.
 */
@Service
public class UserServiceImpl  implements  UserService{

    @Autowired
    private UserRepository userRepository;
    
     //@MyTransactional(rollbackFor = Exception.class)
    @Transactional(rollbackFor = Exception.class)
    @TargetDataSource("ds2")
    public void save(User user) {
        userRepository.save(user);
    //    System.out.println(1/0);
    }
}
