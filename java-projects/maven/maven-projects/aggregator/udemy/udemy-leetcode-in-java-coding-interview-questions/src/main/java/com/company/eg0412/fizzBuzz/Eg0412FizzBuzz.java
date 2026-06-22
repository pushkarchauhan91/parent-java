package com.company.eg0412.fizzBuzz;

import java.util.ArrayList;
import java.util.List;

public class Eg0412FizzBuzz {

    public static void main(String[] args) {
        Eg0412FizzBuzz obj = new Eg0412FizzBuzz();
        System.out.println(obj.fizzBuzz(20));
    }

    public List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                list.add("FizzBuzz");
            } else if (i % 3 == 0) {
                list.add("Fizz");
            } else if (i % 5 == 0) {
                list.add("Buzz");
            } else {
                list.add(String.valueOf(i));
            }
        }
        return list;
    }
}
