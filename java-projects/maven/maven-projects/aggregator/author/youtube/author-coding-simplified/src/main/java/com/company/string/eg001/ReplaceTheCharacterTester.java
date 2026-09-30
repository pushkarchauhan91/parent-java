package com.company.string.eg001;

public class ReplaceTheCharacterTester {

    public static void main(String[] args) {
        String s = "Hello World!! Welcome to Coding Simplified";
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'o' || chars[i] == 'O') {
                chars[i] = 'X';
            }
        }
        System.out.println(chars);
    }
}
