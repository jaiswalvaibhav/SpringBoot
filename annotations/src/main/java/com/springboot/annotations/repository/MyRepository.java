package com.springboot.annotations.repository;

import org.springframework.stereotype.Repository;

@Repository
public class MyRepository {//For all DB interations/logic

    public String hello(){
        return "Hello from Stereotype @Repository";
    }
}
