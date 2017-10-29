package com.moa.consumer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2017/10/27.
 */
@RestController
public class DcController {

	@Autowired
	LoadBalancerClient loadBalancerClient;
	
	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/consumer")
	public String dc() {
		// 先通过loadBalancerClient的choose函数来负载均衡的选出一个eureka-client的服务实例，
		// 这个服务实例的基本信息存储在ServiceInstance中，
		// 然后通过这些对象中的信息拼接出访问/dc接口的详细地址，最后再利用RestTemplate对象实现对服务提供者接口的调用。
		ServiceInstance serviceInstance = loadBalancerClient.choose("eurake-client");
		// 拼接远程的连接url
		String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/dc";
		System.out.println(url);
		return restTemplate.getForObject(url, String.class);
	}
}
