package com.moa.druid.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceProperties;

@Configuration
public abstract class BaseConfig {

	// 默认实体根包 com.xk
	static String ENTITY_BASEPACKAGES = "com.moa.druid";

	@Autowired
	protected JpaVendorAdapter jpaVendorAdapter;

	public abstract DruidDataSourceProperties myDruidDatasourceProperties();

	public DataSource createDataSource(String uniqueResourceName) {
		AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
		// 获取设置
		MyDruidDatasourceProperties druidDataSourceProperties = (MyDruidDatasourceProperties) myDruidDatasourceProperties();
		// 设置驱动数据源
		ds.setXaDataSourceClassName(druidDataSourceProperties.getXaDataSourceClassName());
		// 设置连接池的属性
		ds.setXaProperties(druidDataSourceProperties.getProperties());
		// 设置唯一名称
		ds.setUniqueResourceName(uniqueResourceName);
		// 设置连接池大小
		ds.setMaxPoolSize(10);
		ds.setMinPoolSize(5);
		
		return ds;
	}

	public LocalContainerEntityManagerFactoryBean createEntityManager(Environment env, String uniqueResouceName) {
		HashMap<String, Object> properties = new HashMap<String, Object>();
		// 这个一定需要，否则事务管理不起作用
		properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());

		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		// 设置数据源
		entityManager.setJtaDataSource(createDataSource(uniqueResouceName+"DataSource"));
		// 这个设置
		entityManager.setJpaPropertyMap(properties);

		entityManager.setJpaVendorAdapter(jpaVendorAdapter);
		// 设置entity的包位置
		entityManager.setPackagesToScan(BaseConfig.ENTITY_BASEPACKAGES);
		// 创建持久单元名称， 唯一
		entityManager.setPersistenceUnitName(uniqueResouceName+"Unit");
		return entityManager;
	}
}
