package com.moa.amqp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@EnableTurbineStream
@EnableDiscoveryClient
@SpringBootApplication
public class CloudEurakeTurbineAmqpApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudEurakeTurbineAmqpApplication.class, args);
	}
}
