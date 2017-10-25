package com.moa.multids;

import com.moa.multids.config.DynamicDataSourceRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Import({DynamicDataSourceRegister.class})   // 引用配置
@EnableTransactionManagement
@EnableJpaRepositories(basePackages={"com.moa.multids.repository",""})
public class SpringBootMultidsApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMultidsApplication.class, args);
	}
}
