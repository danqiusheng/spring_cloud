package com.moa.druid.repository;

import com.moa.druid.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OtherRepository extends JpaRepository<User,String>{

}
