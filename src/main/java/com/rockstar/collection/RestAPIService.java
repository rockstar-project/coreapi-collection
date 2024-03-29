package com.rockstar.collection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class RestAPIService {

	public static void main(String[] args) {
		SpringApplication.run(RestAPIService.class, args);
	}
}
