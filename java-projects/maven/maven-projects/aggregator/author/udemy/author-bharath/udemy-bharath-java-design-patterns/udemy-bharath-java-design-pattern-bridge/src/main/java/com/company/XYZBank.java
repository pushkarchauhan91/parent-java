package com.company;

public class XYZBank implements Bank {
    @Override
    public void processPayment(String accountNumber, double amount) {
        System.out.println("Processing payment of " + amount + " through XYZ Bank for account " + accountNumber);
    }
}
