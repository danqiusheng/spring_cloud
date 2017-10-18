package com.moa.datasource.config;

/**
 * Created by Administrator on 2017/10/18.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * 配置user的 oracle 数据源
 */
@Configuration
@DependsOn("transactionManager")
@EnableJpaRepositories(basePackages = "${primary.datasource.basePackages}",
        entityManagerFactoryRef = "userEntityManager",
        transactionManagerRef = "transactionManager")
public class UserConfig {

    @Autowired
    private JpaVendorAdapter jpaVendorAdapter;

    // 设置包
    @Value("${primary.datasource.basePackages}")
    private String basePackages;

    //  获取mysql配置
    @Bean
    @Primary
    @ConfigurationProperties("primary.datasource")
    public DataSourceProperties primaryProperties() {
        return new DataSourceProperties();
    }


    // 第一个数据源
    @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    @Primary
    @ConfigurationProperties(prefix = "primary.datasource")
    public DataSource primaryDataSource() {
        return primaryProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name = "userEntityManager")
    @DependsOn("transactionManager")
    @Primary
    public LocalContainerEntityManagerFactoryBean userEntityManager() throws Throwable {

        HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
        properties.put("hibernate.format_sql", "true");

        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        //设置数据源
        entityManager.setJtaDataSource(primaryDataSource());
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = (HibernateJpaVendorAdapter)jpaVendorAdapter;

        hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.Oracle10gDialect");
        // 默认方言为mysql
        entityManager.setJpaVendorAdapter(jpaVendorAdapter);

        entityManager.setPackagesToScan(basePackages);
        // 创建持久单元名称， 唯一
        entityManager.setPersistenceUnitName("primaryPersistenceUnit");
        // 这个设置一定需要 ，为什么
        entityManager.setJpaPropertyMap(properties);

        return entityManager;
    }
}
