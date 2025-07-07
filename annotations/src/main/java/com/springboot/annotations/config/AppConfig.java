package com.springboot.annotations.config;

import com.springboot.annotations.controller.JavaDIPizzaController;
import com.springboot.annotations.controller.PizzaController;
import com.springboot.annotations.service.JainPizza;
import com.springboot.annotations.service.Pizza;
import com.springboot.annotations.service.VegPizza;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

@Configuration
//@Lazy //Lazy can be used on configuration as well and Configurations as fetched on demand
public class AppConfig {

    @Bean(name = "vegPizzaBean") // Java based configuration
    @Lazy //This veg pizza bean will be loaded on demand
    public Pizza vegPizza(){
        return new VegPizza();
    }

    @Bean
    public Pizza jainPizza(){
        return new JainPizza();
    }

    //DI using Java based configuration - without @Component on controller class and w/o @Autowired on constructor
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public JavaDIPizzaController pizzaController(){
        return new JavaDIPizzaController(jainPizza());
    }
}
