package com.moa.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


// 激活eureka中的DiscoveryClient实现
@EnableDiscoveryClient
@SpringBootApplication
public class CloudEurakeClientApplication {
	public static void main(String[] args) {
		SpringApplication.run(CloudEurakeClientApplication.class, args);
	}
}
