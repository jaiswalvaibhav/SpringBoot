package com.springboot.annotations.service;

import org.springframework.stereotype.Component;

@Component
public class VeganPizza implements Pizza{

    @Override
    public String getPizza() {
        return "Vegan Pizza";
    }
}
