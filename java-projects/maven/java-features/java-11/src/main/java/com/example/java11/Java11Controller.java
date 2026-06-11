package com.example.java11;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Java11Controller {

    @GetMapping("/")
    public String home() {
        return "Hello from java-11 running on Java 11";
    }
}
