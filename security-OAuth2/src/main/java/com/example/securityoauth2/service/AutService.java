package com.example.securityoauth2.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AutService {
    public Map<String,String> authentificate(String grantType,String name,
                                             String pass,boolean withrefrechtoken,
                                             String refrechtoken)
    {
        return  new HashMap<>(); }

}
