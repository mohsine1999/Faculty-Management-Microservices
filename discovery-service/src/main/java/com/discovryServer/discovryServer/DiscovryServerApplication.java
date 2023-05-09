package com.discovryServer.discovryServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
public class DiscovryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscovryServerApplication.class, args);
	}

}
