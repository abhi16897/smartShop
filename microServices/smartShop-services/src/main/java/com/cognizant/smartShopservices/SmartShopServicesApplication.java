package com.cognizant.smartShopservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SmartShopServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartShopServicesApplication.class, args);
	}

}
