package com.nces.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestApplication implements browse {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
        browse.browser("http://localhost:63342/demo/static/form.html");
        // System.out.println("App started");
    }
}
