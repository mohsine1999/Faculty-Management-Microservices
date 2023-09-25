package com.miraouy;

import com.miraouy.config.RsakeysConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.miraouy.ClientFeign")
@EnableConfigurationProperties(RsakeysConfig.class)
public class ServiceNoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceNoteApplication.class, args);
    }

}
