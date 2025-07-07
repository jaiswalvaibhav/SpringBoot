package com.springboot.annotations.service;

public class JainPizza implements Pizza{

    @Override
    public String getPizza() {
        return "Jain Pizza";
    }
}