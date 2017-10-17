package com.moa.datasource.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/17.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "${spring.datasource.secondary.basePackages}",
        entityManagerFactoryRef = "entityManagerFactorySecondary",
        transactionManagerRef = "jtaTransactionManager")
public class SecondaryDataSourceConfig {

    @Value("${spring.datasource.secondary.basePackages}")
    private String basePackages;

    @Bean
    @ConfigurationProperties("spring.datasource.secondary")
    public DataSourceProperties secondaryProperties(){
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.secondary")
    public DataSource othersDataSource(){
        return secondaryProperties().initializeDataSourceBuilder().build();
    }

   /* @Bean
    @ConfigurationProperties("spring.datasource.secondary")
    public DataSource othersDataSource() {
        return DataSourceBuilder.create().build();
    }*/


    @Autowired
    private JpaProperties jpaProperties;

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }

    @Bean(name = "entityManagerFactorySecondary")
    public LocalContainerEntityManagerFactoryBean othersEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(othersDataSource())
                . properties(getVendorProperties(othersDataSource()))
                .persistenceUnit("others")
                .packages(basePackages)
                .build();
    }
}