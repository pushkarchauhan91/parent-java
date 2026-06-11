package com.example.java17;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Java17Controller {

    @GetMapping("/")
    public String home() {
        return "Hello from java-17 running on Java 17";
    }
}
