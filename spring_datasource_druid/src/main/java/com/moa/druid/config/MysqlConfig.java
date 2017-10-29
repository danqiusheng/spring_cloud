package com.moa.druid.config;

/**
 * Created by Administrator on 2017/10/18.
 */

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

/**
 * 配置mysql数据源
 */
@Configuration
@EnableJpaRepositories(basePackages = "${spring.datasource.druid.secondary.jpaBasePackages}", //
		entityManagerFactoryRef = "messageEntityManager")
public class MysqlConfig extends BaseConfig {
	// 区分唯一名称
	@Value("${spring.datasource.druid.secondary.uniqueName}")
	private String uniqueName;

	@Bean
	@ConfigurationProperties("spring.datasource.druid.secondary")
	public DruidDataSourceProperties myDruidDatasourceProperties() {
		return new MyDruidDatasourceProperties();
	}

	@Bean(name = "secondaryDataSource")
	public DataSource createDataSource() {
		return super.createDataSource(uniqueName);
	}

	@Bean(name = "messageEntityManager")
	@DependsOn("transactionManager")
	@Autowired
	public LocalContainerEntityManagerFactoryBean messageEntityManager(Environment env) throws Throwable {
		LocalContainerEntityManagerFactoryBean entityManager = super.createEntityManager(env, uniqueName);
		return entityManager;
	}
}
