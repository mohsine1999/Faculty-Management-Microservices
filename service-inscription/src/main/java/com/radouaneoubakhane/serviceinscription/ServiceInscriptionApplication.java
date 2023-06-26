package com.radouaneoubakhane.serviceinscription;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableFeignClients("com.radouaneoubakhane.serviceinscription.openfeingClients")
@EnableDiscoveryClient

public class ServiceInscriptionApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceInscriptionApplication.class, args);
    }

}
