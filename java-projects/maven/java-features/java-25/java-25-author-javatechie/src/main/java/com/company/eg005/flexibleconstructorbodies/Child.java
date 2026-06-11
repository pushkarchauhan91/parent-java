package com.company.eg005.flexibleconstructorbodies;

public class Child extends Parent {

// Before Java 25 super must be first line of constructor now i can add custom validation logic before super
//    Child(String name, int age) {
//        super(name, age);
//    }

    Child(String name, int age) {
        if (age > 18) {
            System.out.println("Child age is greater than 18");
        }
        super(name, age);
        System.out.println("Child constructor called..");
    }
}
