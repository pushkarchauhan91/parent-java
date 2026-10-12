package com.company.ebay;

import java.util.List;
import java.util.Optional;

public interface BankingSystem {

    // Level 1: Basic Banking Operations

    boolean createAccount(int timestamp, String accountId);

    Optional<Integer> deposit(
            int timestamp,
            String accountId,
            int amount
    );

    Optional<Integer> transfer(
            int timestamp,
            String sourceAccountId,
            String targetAccountId,
            int amount
    );


    // Level 2: Top Spenders

    List<String> topSpenders(int timestamp, int n);


    // Level 3: Payments and Cashback

    Optional<String> pay(
            int timestamp,
            String accountId,
            int amount
    );

    Optional<String> getPaymentStatus(
            int timestamp,
            String accountId,
            String paymentId
    );


    // Level 4: Merge Accounts and Balance History

    boolean mergeAccounts(
            int timestamp,
            String accountId1,
            String accountId2
    );

    Optional<Integer> getBalance(
            int timestamp,
            String accountId,
            int timeAt
    );
}
