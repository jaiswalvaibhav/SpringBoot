package com.springboot.annotations.scope;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)// This is the default configuration. Will work same even if we remove this line
public class SingletonBean {

    public SingletonBean(){
        System.out.println("SingletonBean ..");
    }
}
