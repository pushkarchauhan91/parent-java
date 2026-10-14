package com.company.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {

    // GET /helloworld
    @GetMapping("/helloworld")
    public String greetHelloWorld() {
        return "Hello World!!";
    }

    // GET /hello/Pushkar -> value taken from the URL path
    // http://localhost:8181/hello/pushkar
    @GetMapping("/hello/{name}")
    public String helloPathVariable(@PathVariable String name) {
        return "Hello World!! " + name;
    }

    // GET /hello?name=Pushkar -> value taken from the query string
    // http://localhost:8181/hello?name=pushkar
    @GetMapping("/hello")
    public String helloRequestParam(@RequestParam String name) {
        return "Hello World!! " + name;
    }

}
