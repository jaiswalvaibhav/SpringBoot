package com.springboot.annotations.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Component
//@Primary //Tells IoC container to give higher preference to this bean; Alternative of @Qualifier
//Another way to create bean for this class is Java based configuration in AppConfig
public class VegPizza implements Pizza{

    @Override
    public String getPizza(){
        return "Veg Pizza";
    }
}
