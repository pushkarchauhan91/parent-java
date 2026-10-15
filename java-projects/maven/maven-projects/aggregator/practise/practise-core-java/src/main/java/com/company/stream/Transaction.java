package com.company.stream;

import lombok.Data;

@Data
public class Transaction {

    private String category;
    private double amount;

    public Transaction() {
    }

    public Transaction(String category, double amount) {
        this.category = category;
        this.amount = amount;
    }
}
