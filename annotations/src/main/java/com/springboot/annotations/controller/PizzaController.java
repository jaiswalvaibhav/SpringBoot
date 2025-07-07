package com.springboot.annotations.controller;

import com.springboot.annotations.service.Pizza;
import com.springboot.annotations.service.VegPizza;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("pizza") //Automatically creates spring bean and manages it. We dont need to create new object of this class
public class PizzaController {//bean name by default is pizzaController but custom name overrides it to pizza

/*
    //Autowired should be used at one of below 3 options->

    // -> Option 1 for Autowired
    //The @Autowired annotation is used in field injection
    @Autowired
    private VegPizza vegPizza;

    // -> Option 2 for Autowired
    //The @Autowired annotation is used in constructor injection
    //Most recommended approach for production; Makes dependencies explicit and final; Best for immutability and unit testing.
    @Autowired //on VegPizza spring bean. It will automatically pass the vegPizza object w/o creating an object
    public PizzaController(VegPizza vegPizza){
        this.vegPizza=vegPizza;
    }

    // -> Option 3 for Autowired
    //The @Autowired annotation is used in setter injection
    @Autowired
    public void setVegPizza(VegPizza vegPizza) {
        this.vegPizza = vegPizza;
    }
    */

    private Pizza pizza;//use interface instead of various class object (like VegPizza) to achieve loose coupling

/*
    // -> Here the Spring IoC Container will get confused which object of pizza interface implementation to inject here coz there are multiple implementations
    //So, we can use @Qualifier Annotation with preferred bean name to tell Spring IoC container which bean to inject
//    public PizzaController(@Qualifier("nonVegPizza") Pizza pizza){
    //OR
    // -> Alternative to Qualifier is @Primary -> Non-veg Pizza
*/

    @Autowired //DI
    public PizzaController(Pizza pizza){
        // Pizza interface has 3 beans to choose for DI: Veg (Java Configuration based), Non-veg (Primary - Annotation based), Vegan (Annotation based)
        this.pizza=pizza;
    }
    public String getPizza(){
        return pizza.getPizza();
    }

    //Can be used for inserting records in DB on application startup, opening a connection, initializing a cache, logging something, validating a property
    @PostConstruct
    public void init(){
        System.out.println("Perform these steps after construction/DI/initialisation of beans in PizzaController (used Annotation PostContruct)");
    }

    //If your bean holds resources that need to be explicitly released — like deleting inserted records in DB on application shutdown, closing a database connection, stopping a thread, cleaning up files
    @PreDestroy
    public void destroy(){
        System.out.println("Perform these steps before destroying beans in PizzaController (using Annotation PreDestroy)");
    }
}
