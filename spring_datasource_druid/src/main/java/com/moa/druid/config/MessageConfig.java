package com.moa.druid.config;

/**
 * Created by Administrator on 2017/10/18.
 */

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Properties;

/**
 * 配置mssage的mysql数据源
 */
@Configuration
@DependsOn("transactionManager")
@EnableJpaRepositories(basePackages = "${spring.datasource.druid.secondary.basePackages}",
        entityManagerFactoryRef = "messageEntityManager",
        transactionManagerRef = "transactionManager")
public class MessageConfig {

    // 设置包
    @Value("${spring.datasource.druid.secondary.basePackages}")
    private String basePackages;


    @Value("${spring.datasource.druid.secondary.platform}")
    private String platform;

    @Bean
    @ConfigurationProperties("spring.datasource.druid.secondary")
    public DruidDataSourceProperties myDruidDatasourceProperties() {
        return new MyDruidDatasourceProperties();
    }

    @Autowired
    private JpaVendorAdapter jpaVendorAdapter;

    @Bean(name = "secondaryDataSource")
    @Autowired
    public DataSource secondaryDataSource(Environment env) {
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        // 获取设置
        MyDruidDatasourceProperties druidDataSourceProperties = (MyDruidDatasourceProperties) myDruidDatasourceProperties();
        // 设置驱动数据源
        ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        // 设置唯一名
        ds.setUniqueResourceName("secondaryDataSource");
        //　设置池大小　－－－　加入设置
        ds.setPoolSize(5);
        ds.setXaProperties(druidDataSourceProperties.getProperties());
        return ds;

    }


    @Bean(name = "messageEntityManager")
    @DependsOn("transactionManager")
    @Autowired
    public LocalContainerEntityManagerFactoryBean messageEntityManager(Environment env) throws Throwable {
        HashMap<String, Object> properties = new HashMap<String, Object>();
        // 这个一定需要，否则事务管理不起作用
        properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
        // 格式化
        properties.put("hibernate.format_sql", "true");
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();

        //设置数据源
        entityManager.setJtaDataSource(secondaryDataSource(env));
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = (HibernateJpaVendorAdapter) jpaVendorAdapter;
        hibernateJpaVendorAdapter.setDatabasePlatform(platform);
        entityManager.setJpaVendorAdapter(jpaVendorAdapter);
        entityManager.setPackagesToScan(basePackages);

        // 创建持久单元名称， 唯一
        entityManager.setPersistenceUnitName("secondarrPersistenceUnit");
        // 这个设置
        entityManager.setJpaPropertyMap(properties);

        return entityManager;
    }

}
