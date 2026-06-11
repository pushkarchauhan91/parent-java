package com.example.java21;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Java21Controller {

    @GetMapping("/")
    public String home() {
        return "Hello from java-21 running on Java 21";
    }
}
