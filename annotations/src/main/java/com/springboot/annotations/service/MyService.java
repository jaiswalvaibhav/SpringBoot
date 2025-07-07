package com.springboot.annotations.service;

import org.springframework.stereotype.Service;

@Service
public class MyService {//For all business logic

    public String hello(){
        return "Hello from Stereotype @Service";
    }
}
