package com.moa.dao;

import com.moa.model.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Retention;

/**
 * Created by Administrator on 2017/10/17.
 */
 @Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);

    User findByNameAndAge(String name, Integer age);

    @Query(value = "from User u where u.name=:name")
    User findUser(@Param("name") String name);
}