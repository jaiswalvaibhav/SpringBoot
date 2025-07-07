package com.springboot.annotations.controller;

import com.springboot.annotations.service.Pizza;

public class JavaDIPizzaController {//bean name by default is pizzaController but custom name overrides it to pizza

    private Pizza pizza;
    public JavaDIPizzaController(Pizza pizza){
        // Pizza interface has 3 beans to choose for DI: Veg (Java Configuration based), Non-veg (Primary - Annotation based), Vegan (Annotation based)
        this.pizza=pizza;
    }
    public String getPizza(){
        return pizza.getPizza();
    }

    //Can be used for inserting records in DB on application startup, opening a connection, initializing a cache, logging something, validating a property,
    public void init(){
        System.out.println("Perform these steps after construction/DI/initialisation of beans in JavaDIPizzaController (used Java Based Config initMethod)");
    }

    //If your bean holds resources that need to be explicitly released — like deleting inserted records in DB on application shutdown,, like closing a database connection, stopping a thread, cleaning up files
    public void destroy(){
        System.out.println("Perform these steps before destroying beans in JavaDIPizzaController (used Java Based Config destroyMethod)");
    }
}
