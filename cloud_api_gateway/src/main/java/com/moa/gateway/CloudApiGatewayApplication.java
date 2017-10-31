package com.moa.gateway;

import com.moa.gateway.filter.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

//开启Zuul的功能
@EnableZuulProxy
@SpringBootApplication
public class CloudApiGatewayApplication {
	@Bean
	public AccessFilter accessFilter() {
		return new AccessFilter();
	}
	public static void main(String[] args) {
		SpringApplication.run(CloudApiGatewayApplication.class, args);
	}
}
