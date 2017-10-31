package com.moa.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@EnableAutoConfiguration
@EnableTurbine
@EnableDiscoveryClient
@SpringBootApplication
public class CloudEurakeTurbineApplication {
	public static void main(String[] args) {
		SpringApplication.run(CloudEurakeTurbineApplication.class, args);
	}
}
