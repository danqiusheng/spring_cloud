package com.moa.datasource.primary.service;

import com.moa.datasource.primary.dao.UserRepository;
import com.moa.datasource.primary.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/10/17.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public  void save(User user){
        userRepository.save(user);
    }
}
