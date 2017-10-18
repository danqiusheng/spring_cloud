package com.moa.datasource;

import java.util.HashMap;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import com.moa.datasource.repository.order.OrderDatasourceProperties;
import oracle.jdbc.xa.client.OracleXADataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@DependsOn("transactionManager")
@EnableJpaRepositories(basePackages = "com.moa.datasource.repository.order",
		entityManagerFactoryRef = "orderEntityManager",
		transactionManagerRef = "transactionManager")
@EnableConfigurationProperties(OrderDatasourceProperties.class)
public class OrderConfig {


	@Autowired
	private JpaVendorAdapter jpaVendorAdapter;

	@Autowired
	private OrderDatasourceProperties orderDatasourceProperties;

	@Bean(name = "orderDataSource", initMethod = "init", destroyMethod = "close")
	@Primary
	public DataSource orderDataSource() throws  Exception{
		/*JdbcDataSource h2XaDataSource = new JdbcDataSource();
		h2XaDataSource.setURL(orderDatasourceProperties.getUrl());

		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource(h2XaDataSource);
		xaDataSource.setUniqueResourceName("xads2");*/
		OracleXADataSource oracleXADataSource = new OracleXADataSource();
		oracleXADataSource.setUser(orderDatasourceProperties.getUsername());
		oracleXADataSource.setURL(orderDatasourceProperties.getUrl());
		oracleXADataSource.setPassword(orderDatasourceProperties.getPassword());

		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource(oracleXADataSource);
		xaDataSource.setUniqueResourceName("xads2");
		xaDataSource.setPoolSize(10);

		return xaDataSource;
	}

	@Bean(name = "orderEntityManager")
	@Primary
	public LocalContainerEntityManagerFactoryBean orderEntityManager() throws Throwable {

		HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
	    //properties.put("javax.persistence.transactionType", "JTA");

		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setJtaDataSource(orderDataSource());
		HibernateJpaVendorAdapter  hibernateJpaVendorAdapter = (HibernateJpaVendorAdapter)jpaVendorAdapter;
		System.out.println(hibernateJpaVendorAdapter);
		hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.Oracle10gDialect");
		entityManager.setJpaVendorAdapter(jpaVendorAdapter);
		entityManager.setPackagesToScan("com.moa.datasource.model.order");
		entityManager.setPersistenceUnitName("orderPersistenceUnit");
		entityManager.setJpaPropertyMap(properties);
		return entityManager;
	}

}
