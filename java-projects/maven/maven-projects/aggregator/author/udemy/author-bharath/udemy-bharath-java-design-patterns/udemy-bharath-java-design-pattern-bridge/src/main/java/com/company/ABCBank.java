package com.company;

public class ABCBank implements Bank {
    @Override
    public void processPayment(String accountNumber, double amount) {
        System.out.println("Processing payment of " + amount + " through ABC Bank for account " + accountNumber);
    }
}
