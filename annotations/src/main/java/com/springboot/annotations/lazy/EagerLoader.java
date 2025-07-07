package com.springboot.annotations.lazy;

import org.springframework.stereotype.Component;

@Component
public class EagerLoader {

    public EagerLoader(){
        System.out.println("EagerLoader Bean loaded while startup/bootstrapping of the application context...");
    }
}
