package com.moa.druid.config;

/**
 * Created by Administrator on 2017/10/18.
 */

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
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

import javax.persistence.PostRemove;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Properties;

/**
 * 配置user的 oracle 数据源
 */
@Configuration
@DependsOn("transactionManager")
@EnableJpaRepositories(basePackages = "${spring.datasource.druid.primary.basePackages}",
        entityManagerFactoryRef = "userEntityManager",
        transactionManagerRef = "transactionManager")
public class UserConfig {



    @Autowired
    private JpaVendorAdapter jpaVendorAdapter;


    @Value("${spring.datasource.druid.primary.platform}")
    private String platform;


    // 设置包
    @Value("${spring.datasource.druid.primary.basePackages}")
    private String basePackages;

    @ConfigurationProperties("spring.datasource.druid.primary")
    @Bean
    @Primary
   public  DruidDataSourceProperties myPrimaryDruidDatasourceProperties(){
       return new MyDruidDatasourceProperties();
   }

    @Bean(name = "primaryDataSource")
    @Primary
    @Autowired
    public DataSource primaryDataSource(Environment env) {
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        MyDruidDatasourceProperties druidDataSourceProperties = (MyDruidDatasourceProperties)myPrimaryDruidDatasourceProperties();
        // 设置驱动数据源
        ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        // 设置名
        ds.setUniqueResourceName("primaryDataSource");
        ds.setPoolSize(5);
        ds.setXaProperties(druidDataSourceProperties.getProperties());
        return ds;

    }

    @Bean

    @ConfigurationProperties("spring.datasource.druid")
    public JpaVendorAdapter primaryJpaVendorAdapter(){
        return new HibernateJpaVendorAdapter();

    }


    @Bean(name = "userEntityManager")
    @DependsOn("transactionManager")
    @Primary
    @Autowired
    public LocalContainerEntityManagerFactoryBean userEntityManager(Environment env) throws Throwable {

        HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
        properties.put("hibernate.format_sql", "true");

        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        //设置数据源
        entityManager.setJtaDataSource(primaryDataSource(env));
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = (HibernateJpaVendorAdapter) jpaVendorAdapter;
        hibernateJpaVendorAdapter.setDatabasePlatform(platform);

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
