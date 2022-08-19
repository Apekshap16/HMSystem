package com.hms.ownerms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OwnerMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OwnerMsApplication.class, args);
	}

}
