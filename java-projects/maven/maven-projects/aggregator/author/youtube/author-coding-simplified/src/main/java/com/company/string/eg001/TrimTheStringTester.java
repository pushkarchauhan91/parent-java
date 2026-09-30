package com.company.string.eg001;

public class TrimTheStringTester {

    public static void main(String[] args) {
        String s = " Welcome     to   Bangalore   ";
        char[] chars = s.toCharArray();
        int startIndex = -1, endIndex = -1, count = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                startIndex = i;
                break;
            }
        }

        for (int i = chars.length - 1; i > startIndex - 1; i--) {
            if (chars[i] != ' ') {
                endIndex = i;
                break;
            }
        }

        count = endIndex - startIndex + 1;

        String result = new String(chars, startIndex, count);
        System.out.println(result);
    }
}
