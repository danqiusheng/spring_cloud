package com.moa.datasource.secondary.repository;

import com.moa.datasource.secondary.model.MessageInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/10/18.
 */
@Repository
public interface MessageRepository  extends JpaRepository<MessageInfo,String>{
}