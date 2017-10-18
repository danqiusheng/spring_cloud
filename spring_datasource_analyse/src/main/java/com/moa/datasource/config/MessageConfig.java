package com.moa.datasource.config;

/**
 * Created by Administrator on 2017/10/18.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
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
 * 配置mssage的mysql数据源
 */
@Configuration
@DependsOn("transactionManager")
@EnableJpaRepositories(basePackages = "${secondary.datasource.basePackages}",
        entityManagerFactoryRef = "messageEntityManager",
        transactionManagerRef = "transactionManager")
public class MessageConfig {
    @Autowired
    private JpaVendorAdapter jpaVendorAdapter;

    // 设置包
    @Value("${secondary.datasource.basePackages}")
    private String basePackages;

    //  获取mysql配置
    @Bean
    @ConfigurationProperties("secondary.datasource")
    public DataSourceProperties secondaryProperties() {
        return new DataSourceProperties();
    }


    // 第二个数据源
    @Bean(name = "secondaryDataSource")
    @Qualifier("secondaryDataSource")
    @ConfigurationProperties(prefix = "secondary.datasource")
    public DataSource secondaryDataSource() {
        return secondaryProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name = "messageEntityManager")
    @DependsOn("transactionManager")
    public LocalContainerEntityManagerFactoryBean messageEntityManager() throws Throwable {

        HashMap<String, Object> properties = new HashMap<String, Object>();
        // 这个一定需要，否则事务管理不起作用
        properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());

        // 格式化
        properties.put("hibernate.format_sql", "true");

        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        //设置数据源
        entityManager.setJtaDataSource(secondaryDataSource());
        // 默认方言为mysql
        entityManager.setJpaVendorAdapter(jpaVendorAdapter);
        entityManager.setPackagesToScan(basePackages);
        // 创建持久单元名称， 唯一
        entityManager.setPersistenceUnitName("secondarrPersistenceUnit");
        // 这个设置
        entityManager.setJpaPropertyMap(properties);

        return entityManager;
    }

}
