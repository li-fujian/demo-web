package com.lifujian.webdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WelcomeController {

	public static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

	// http://localhost:8080/isalive
    @RequestMapping("/isalive")
    @ResponseBody
    public String isalive(){
        return "ok";
    }

}
