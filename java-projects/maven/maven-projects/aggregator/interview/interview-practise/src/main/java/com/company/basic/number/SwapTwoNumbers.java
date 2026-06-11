package com.company.basic.number;

public class SwapTwoNumbers {

    public static void main(String[] args) {
        SwapTwoNumbers swapTwoNumbers = new SwapTwoNumbers();
        int a = 1;
        int b = 2;
        swapTwoNumbers.swapUsingTemporaryVariable(1, 2);
        swapTwoNumbers.swapUsingXOR(1, 2);
    }

    public void swapUsingTemporaryVariable(int a, int b) {
        System.out.println(a + " " + b);
        int temp = a;
        a = b;
        b = temp;
        System.out.println(a + " " + b);
    }

    public void swapUsingXOR(int a, int b) {
        System.out.println(a + " " + b);
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a + " " + b);
    }
}
