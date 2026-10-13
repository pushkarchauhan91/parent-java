package com.company;

public abstract class Payment {
    protected Bank bank;

    public Payment(Bank bank) {
        this.bank = bank;
    }

    public abstract void makePayment(String accountNumber, double amount);
}
