package com.company.eg005.flexibleconstructorbodies;

public class Parent {

    String name;
    int age;

    Parent(String name, int age) {
        System.out.println("Parent constructor called...");
        this.name = name;
        this.age = age;
    }
}
