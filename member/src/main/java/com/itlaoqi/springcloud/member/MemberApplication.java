package com.itlaoqi.springcloud.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients//启用Feign客户端
@EnableCircuitBreaker //断路器
/**
 * 与配置中心的调整
 * 1. application.yml删掉，新增bootstrap.yml，内容详见文件
 * 2. pom.xml增加config-client
 <dependency>
 	<groupId>org.springframework.cloud</groupId>
 	<artifactId>spring-cloud-config-client</artifactId>
 </dependency>
  */
public class MemberApplication {

	@Bean //将返回的RestTemplate对象注入到IOC容器中
	@LoadBalanced //对RestTemplate进行负载均衡
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(MemberApplication.class, args);
	}
}
