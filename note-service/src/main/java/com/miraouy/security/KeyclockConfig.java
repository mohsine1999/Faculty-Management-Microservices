package com.miraouy.security;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration

public class KeyclockConfig {
    // pour dire a applic que la configuration de keyclock se trouve dans application.prop
    @Bean
    KeycloakSpringBootConfigResolver springBootConfigResolver(){
        return  new KeycloakSpringBootConfigResolver();
    }

}
