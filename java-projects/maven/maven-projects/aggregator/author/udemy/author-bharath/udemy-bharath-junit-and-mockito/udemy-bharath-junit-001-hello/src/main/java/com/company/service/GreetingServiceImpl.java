package com.company.service;

public class GreetingServiceImpl implements Greeting {

    @Override
    public String greet(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return "Hello " + name;
    }
}
