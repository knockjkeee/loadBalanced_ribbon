package com.test.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/book")
public class PortBookApplication {

    @Value("${server.port}")
    private String port;


    @GetMapping("/port")
    public String chatNow(){
        return "application is uo in port : " + port;
    }

    @GetMapping("/")
    public String refresh(){
        return "";
    }


    public static void main(String[] args) {
        SpringApplication.run(PortBookApplication.class, args);
    }

}
