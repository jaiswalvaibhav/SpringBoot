package com.springboot.annotations.propertysource;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
/*
@PropertySource("classpath:mail.properties")//provide custom property file to this configuration class in spring env
@PropertySource("classpath:messages.properties")//Loading multiple properties files on same configuration class
*/
//We can use above way to use multiple .properties file OR use @PropertySources
@PropertySources({
    @PropertySource("classpath:mail.properties"),
    @PropertySource("classpath:messages.properties")
})

public class SpringConfig {
}
