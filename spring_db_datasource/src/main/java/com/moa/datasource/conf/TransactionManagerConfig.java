package com.moa.datasource.conf;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.UserTransaction;

/**
 * Created by Administrator on 2017/10/17.
 */
@Configuration
@EnableTransactionManagement
public class TransactionManagerConfig {
    @Bean(destroyMethod = "close", initMethod = "init")
    public UserTransactionManager userTransactionManager() {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(true);
        return userTransactionManager;
    }

    @Bean
    public UserTransaction userTransaction() throws Throwable {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(30000);
        return userTransactionImp;
    }

    @Bean
    public PlatformTransactionManager jtaTransactionManager() throws Throwable {

        UserTransactionManager userTransactionManager =  userTransactionManager();
        AtomikosJtaPlatform.transactionManager = userTransactionManager;
        UserTransaction userTransaction =   userTransaction();
        AtomikosJtaPlatform.transaction = userTransaction;

        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
        jtaTransactionManager.setTransactionManager(userTransactionManager);
        jtaTransactionManager.setUserTransaction(userTransaction);

        return jtaTransactionManager;
    }
}
