package com.moa.service;

import com.moa.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;

/**
 * Created by Administrator on 2017/10/17.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void create(String name, Integer age) {

    }

    @Override
    @Transactional()
    public void deleteByName(String name) {

    }

    @Override
    @Transactional(readOnly = true)
    public Integer getAllUsers() {
        return null;
    }

    @Override
    @Transactional
    public void deleteAllUsers() {

    }
}
