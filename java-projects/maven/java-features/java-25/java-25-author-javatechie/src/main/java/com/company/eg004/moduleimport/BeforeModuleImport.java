package com.company.eg004.moduleimport;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BeforeModuleImport {

    void main() {
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        System.out.println(fruits.size());

        System.out.println(new Random().nextInt(100));
    }
}
