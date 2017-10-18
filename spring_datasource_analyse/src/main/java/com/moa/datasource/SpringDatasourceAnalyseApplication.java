package com.moa.datasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.moa")
public class SpringDatasourceAnalyseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDatasourceAnalyseApplication.class, args);
	}
}
