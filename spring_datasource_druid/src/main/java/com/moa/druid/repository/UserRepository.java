package com.moa.druid.repository;

import com.moa.druid.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/10/18.
 */
@Repository
public interface UserRepository  extends JpaRepository<User,String>{
}
