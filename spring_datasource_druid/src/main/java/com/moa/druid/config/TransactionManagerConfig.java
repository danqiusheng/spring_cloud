package com.moa.druid.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

/**
 * Created by Administrator on 2017/10/17.
 */
@Configuration
@ComponentScan
@EnableTransactionManagement
public class TransactionManagerConfig {

    /**
     * 需要测试这个是做什么
     * @return
     */
  /*  @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }*/


    /**
     *  创建atomkiosbean
     * @return
     * @throws Throwable
     */
    @Bean(name = "userTransaction")
    public UserTransaction userTransaction() throws Throwable {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(10000);
        return userTransactionImp;
    }


    @Bean(name = "atomikosTransactionManager", initMethod = "init", destroyMethod = "close")
    public TransactionManager atomikosTransactionManager() throws Throwable {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(false);

        AtomikosJtaPlatform.transactionManager = userTransactionManager;

        return userTransactionManager;
    }

    @Bean(name = "transactionManager")
    @DependsOn({ "userTransaction", "atomikosTransactionManager" })
    public PlatformTransactionManager transactionManager() throws Throwable {
        UserTransaction userTransaction = userTransaction();
        AtomikosJtaPlatform.transaction = userTransaction;
        TransactionManager atomikosTransactionManager = atomikosTransactionManager();
        JtaTransactionManager jtaTransactionManager =   new JtaTransactionManager(userTransaction, atomikosTransactionManager);
        jtaTransactionManager.setAllowCustomIsolationLevels(true);
        return jtaTransactionManager;
    }

    /**
     * 在这里创建jpaVendor  后者用调用只需要更新方言
     * 默认创建mysql方言
     */
    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        //HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        // 默认开启打印SQL
       // hibernateJpaVendorAdapter.setShowSql(true);
        //hibernateJpaVendorAdapter.setGenerateDdl(true);
      //  hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
        return new HibernateJpaVendorAdapter();
    }
}
