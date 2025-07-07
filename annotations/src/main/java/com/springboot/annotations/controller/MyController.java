package com.springboot.annotations.controller;

import org.springframework.stereotype.Controller;

@Controller
public class MyController {//For handling http requests

    public String hello(){

        return "Hello from Stereotype @Controller";
    }
}
