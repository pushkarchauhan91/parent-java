package com.company.string.eg001;

public class PrintFirstCharacterOfEveryWordTester {

    public static void main(String[] args) {
        String s = "Hello User!! Welcome";
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                if (i == 0) {
                    System.out.println(chars[i]);
                } else if (chars[i - 1] == ' ') {
                    System.out.println(chars[i]);
                }
            }
        }
        System.out.println();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ' && (i == 0 || chars[i - 1] == ' ')) {
                System.out.println(chars[i]);
            }
        }
    }
}
