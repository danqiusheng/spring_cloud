package com.moa.multids.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/10/22.
 */

@Aspect
@Component
public class DynamicTransactionAspect {
    @Autowired
    private PlatformTransactionManager transactionManager;

    @Before("@annotation(transactional)")
    public void startTransaction(JoinPoint point, Transactional transactional){
        System.out.println("开始事务");
    }

    @After("@annotation(transactional)")
    public void endTransaction(JoinPoint point, Transactional transactional){

        System.out.println("结束事务");
    }


}

