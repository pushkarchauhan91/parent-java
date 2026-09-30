package com.company.string.eg001;

public class AsciiTester {

    public static void main(String[] args) {
        for (int i = 'a'; i <= 'z'; i++) {
            System.out.println((char)i + " " + i);
        }
        System.out.println();
        for (int i = 'A'; i <= 'Z'; i++) {
            System.out.println((char)i + " " + i);
        }
    }
}
