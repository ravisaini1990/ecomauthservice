package com.ravi.ramzanauthservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RamzanAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RamzanAuthServiceApplication.class, args);
	}

}
