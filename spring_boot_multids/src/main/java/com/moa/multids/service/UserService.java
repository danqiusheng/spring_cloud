package com.moa.multids.service;

import com.moa.multids.config.TargetDataSource;
import com.moa.multids.model.User;
import com.moa.multids.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/10/20.
 */
public interface UserService {

    void save(User user);
}
