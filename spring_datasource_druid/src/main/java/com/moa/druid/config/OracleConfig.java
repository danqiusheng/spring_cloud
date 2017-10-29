package com.moa.druid.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

/**
 * Created by Administrator on 2017/10/18.
 */

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceProperties;

/**
 * 配置 oracle 数据源
 */
@Configuration
@EnableJpaRepositories(basePackages = "${spring.datasource.druid.primary.jpaBasePackages}", //
		entityManagerFactoryRef = "userEntityManager")
public class OracleConfig extends BaseConfig {
  
	  //  
    @Value("${spring.datasource.druid.primary.uniqueName}")
    private String uniqueName;
    
	
	@Bean("primaryDataSourceProperties")
	@Primary
	@ConfigurationProperties("spring.datasource.druid.primary")
	public DruidDataSourceProperties myDruidDatasourceProperties() {
		return new MyDruidDatasourceProperties();
	}

	@Bean(name = "primaryDataSource")
	@Primary
	public DataSource createDataSource() {
		return super.createDataSource(uniqueName);
	}

	@DependsOn("transactionManager")
	@Bean(name = "userEntityManager")
	@Primary
	@Autowired
	public LocalContainerEntityManagerFactoryBean createEntityManager(Environment env) throws Throwable {
		LocalContainerEntityManagerFactoryBean entityManager = super.createEntityManager(env, uniqueName);
		return entityManager;
	}

 
}
