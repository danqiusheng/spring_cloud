package com.moa.druid.primary.repository;

import com.moa.druid.primary.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/10/18.
 */
@Repository
public interface UserRepository  extends JpaRepository<User,String>{
}
