package com.company;

public class CreditCardPayment extends Payment {
    public CreditCardPayment(Bank bank) {
        super(bank);
    }

    @Override
    public void makePayment(String accountNumber, double amount) {
        System.out.println("Using Credit Card Payment...");
        bank.processPayment(accountNumber, amount);
    }
}
