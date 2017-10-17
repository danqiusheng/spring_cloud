package com.moa.datasource.primary.dao;

import com.moa.datasource.primary.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/10/17.
 * @author moa
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long>{
}
