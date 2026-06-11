package com.company.eg004.moduleimport;

import module java.base;

public class AfterModuleImport {

    void main() {
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        System.out.println(fruits.size());

        System.out.println(new Random().nextInt(100));
    }
}