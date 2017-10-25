package com.moa.multids.service;

import com.moa.multids.config.MyTransactional;
import com.moa.multids.config.TargetDataSource;
import com.moa.multids.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/10/23.
 */
@Service
public class TotalService {

    @Autowired
    private UserService userService;


    @Autowired
    private AnotherUserService anotherUserService;


    @MyTransactional(rollbackFor=Exception.class,propagation = Propagation.REQUIRED)
     //@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void test() {
        System.out.println("hahaha");
        userService.save(new User("xx","xxx",1));
        anotherUserService.save(new User("xx111","xxx",1));

    }

}
