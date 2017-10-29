package com.moa.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


// 注解用来将当前应用加入到服务治理体系中
@EnableDiscoveryClient
@SpringBootApplication
public class CloudEurakeConsumerApplication {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(CloudEurakeConsumerApplication.class, args);
	}
}
