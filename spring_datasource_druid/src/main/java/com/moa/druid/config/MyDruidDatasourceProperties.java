package com.moa.druid.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceProperties;
import org.springframework.beans.factory.annotation.Value;

import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Administrator on 2017/10/18.
 */
public class MyDruidDatasourceProperties extends DruidDataSourceProperties {



    @Value("${spring.datasource.druid.XaDataSourceClassName}")
    private String xaDataSourceClassName;

    /**
     * 获取所有属性的properties
     * @return
     */
    public  Properties getProperties(){
        Properties prop = new Properties();
        if (getUrl() != null) {
            prop.put("url",getUrl() );
        }
        if (getUsername() != null) {
            prop.put("username",getUsername());
        }
        if (getPassword() != null) {
            prop.put("password",getPassword());
        }
        if (getDriverClassName() != null) {
            prop.put("driverClassName",getDriverClassName());
        }
        if (getInitialSize() != null) {
            prop.put("initialSize", getInitialSize());
        }
        if (getMaxActive() != null) {
            prop.put("maxActive", getMaxActive());
        }
        if (getMinIdle() != null) {
            prop.put("minIdle",getMinIdle());
        }
        if (getMaxWait() != null) {
            prop.put("maxWait",getMaxWait());
        }
        if (getPoolPreparedStatements() != null) {
            prop.put("poolPreparedStatements", getPoolPreparedStatements());
        }
        if (getMaxOpenPreparedStatements() != null) {
            prop.put("maxPoolPreparedStatementPerConnectionSize",getMaxOpenPreparedStatements());
        }
        if (getMaxPoolPreparedStatementPerConnectionSize() != null) {
            prop.put("maxPoolPreparedStatementPerConnectionSize",getMaxPoolPreparedStatementPerConnectionSize());
        }
        if (getValidationQuery() != null) {
            prop.put("validationQuery",getValidationQuery());
        }
        if (getValidationQueryTimeout() != null) {
            prop.put("validationQueryTimeout",getValidationQueryTimeout());
        }
        if (getTestWhileIdle() != null) {
            prop.put("testWhileIdle",getTestWhileIdle());
        }
        if (getTestOnBorrow() != null) {
            prop.put("testOnBorrow", getTestOnBorrow());
        }
        if (getTestOnReturn() != null) {
            prop.put("testOnReturn",getTestOnReturn());
        }
        if (getTimeBetweenEvictionRunsMillis() != null) {
            prop.put("timeBetweenEvictionRunsMillis",getTimeBetweenEvictionRunsMillis());
        }
        if (getMinEvictableIdleTimeMillis() != null) {
            prop.put("MinEvictableIdleTimeMillis",getMinEvictableIdleTimeMillis());
        }
        if (getMaxEvictableIdleTimeMillis() != null) {
            prop.put("maxEvictableIdleTimeMillis",getMaxEvictableIdleTimeMillis());
        }
        try {
            if (getFilters() != null) {
                prop.put("filters",getFilters());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop;
    }


    public String getXaDataSourceClassName() {
        return xaDataSourceClassName;
    }
}
