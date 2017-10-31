package com.moa.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

// 开启Hystrix Dashboard
@EnableHystrixDashboard
@SpringBootApplication
public class CloudEurakeHystrixDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudEurakeHystrixDashboardApplication.class, args);
	}
}
