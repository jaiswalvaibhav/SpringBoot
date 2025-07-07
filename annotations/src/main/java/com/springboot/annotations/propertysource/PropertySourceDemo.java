package com.springboot.annotations.propertysource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class PropertySourceDemo {

    @Autowired
    private Environment environment;

    //Values from mail.properties
/*
    @Value("${gmail.host}")
    private String host;

    @Value("${gmail.email}")
    private String email;

    @Value("${gmail.password}")
    private String password;

    //These commented
*/

    public String getHost() {
        return environment.getProperty("gmail.host");
    }

    public String getEmail() {
        return environment.getProperty("gmail.email");
    }

    public String getPassword() {
        return environment.getProperty("gmail.password");
    }

    //Values from messages.properties

    @Value("${app.name}")
    private String appName;

    @Value("${app.description}")
    private String appDescription;

    public String getAppDescription() {
        return appDescription;
    }

    public String getAppName() {
        return appName;
    }

}
