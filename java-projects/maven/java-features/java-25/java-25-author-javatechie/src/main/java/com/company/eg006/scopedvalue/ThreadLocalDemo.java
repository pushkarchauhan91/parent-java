package com.company.eg006.scopedvalue;

public class ThreadLocalDemo {
    void main() {

        ThreadLocal<String> currentUser = new ThreadLocal<>();
        currentUser.set("Alice");

        new Thread(() -> {
            currentUser.set("Alice");
            System.out.println("User 1: " + currentUser.get());
        }).start();

        new Thread(() -> {
            currentUser.set("Alice");
            System.out.println("User 2: " + currentUser.get());
        }).start();

    }

}