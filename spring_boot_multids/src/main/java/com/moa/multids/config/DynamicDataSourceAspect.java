package com.moa.multids.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;


/**
 * Created by Administrator on 2017/10/20.
 */
@Aspect
@Order(-10)//保证该AOP在@Transactional之前执行
@Component
public class DynamicDataSourceAspect {

    protected Logger logger = LoggerFactory.getLogger(getClass());
       /*
        * @Before("@annotation(ds)")的意思是：
        * @Before：在方法执行之前进行执行： @annotation(targetDataSource)：
        * 会拦截注解targetDataSource的方法，否则不拦截;
        */
       @Autowired
       private PlatformTransactionManager transactionManager;

    @Before("@annotation(targetDataSource)")
    public void changeDataSource(JoinPoint point, TargetDataSource targetDataSource) throws Throwable {
        //获取当前的指定的数据源;
        String dsId = targetDataSource.value();
        if(dsId.equals("none")){
            return;
        }

        System.out.println("------------"+dsId);
        //如果不在我们注入的所有的数据源范围之内，那么输出警告信息，系统自动使用默认的数据源。
        if (!DynamicDataSourceContextHolder.containsDataSource(dsId)) {
            logger.info("数据源[{}]不存在，使用默认数据源 > {}"+targetDataSource.value()+":"+point.getSignature());
        } else {
            logger.info("使用数据源 UserDataSource:{} >{} "+targetDataSource.value()+":"+point.getSignature());
            //找到的话，那么设置到动态数据源上下文中
            DynamicDataSourceContextHolder.setDataSourceType(targetDataSource.value());
        }
    }



    @After("@annotation(targetDataSource)")
    public void restoreDataSource(JoinPoint point, TargetDataSource targetDataSource) {
        logger.info("RevertDataSource : {} > {}"+targetDataSource.value()+":"+point.getSignature());
        //方法执行完毕之后，销毁当前数据源信息，进行垃圾回收。
        DynamicDataSourceContextHolder.clearDataSourceType();

    }

}
