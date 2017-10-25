package com.moa.multids.repository;

import com.moa.multids.config.TargetDataSource;
import com.moa.multids.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.sql.DataSourceDefinition;

/**
 * Created by Administrator on 2017/10/18.
 */
@Repository
public interface UserRepository extends JpaRepository<User,String>{

    @TargetDataSource("ds2")
    public User save(User user);
}
