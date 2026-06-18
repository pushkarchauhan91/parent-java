package com.company.string;

public class StringTester {

    static void main() {
        StringTester obj = new StringTester();
        String str = "My name is Pushkar Chauhan";
        obj.reverseStringUsingStringBuilder(str);
        obj.reverseStringUsingStringBuffer(str);
        obj.reverseString(str);
    }

    public void reverseStringUsingStringBuilder(String s) {
        System.out.println(new StringBuilder(s).reverse());
    }

    public void reverseStringUsingStringBuffer(String s) {
        System.out.println(new StringBuffer(s).reverse());
    }

    public void reverseString(String s) {
        for (int i = s.length() - 1; i >= 0; i--) {
            System.out.print(s.charAt(i));
        }
    }
}
