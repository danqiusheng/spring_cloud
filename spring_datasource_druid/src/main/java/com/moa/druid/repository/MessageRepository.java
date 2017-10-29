package com.moa.druid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moa.druid.primary.model.MessageInfo;

/**
 * Created by Administrator on 2017/10/18.
 */
@Repository
public interface MessageRepository  extends JpaRepository<MessageInfo,String>{
}
