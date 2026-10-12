package com.company.ebay;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BankingSystemImplTest {

    @Order(1)
    @Test
    void level1AccountsAndTransfers() {
        BankingSystemImpl b = new BankingSystemImpl();
        assertTrue(b.createAccount(1, "account1"));
        assertFalse(b.createAccount(2, "account1"));
        assertTrue(b.createAccount(3, "account2"));
        assertEquals(Optional.empty(), b.deposit(4, "missing", 10));
        assertEquals(Optional.of(2700), b.deposit(5, "account1", 2700));
        assertEquals(Optional.empty(), b.transfer(6, "account1", "account2", 2701));
        assertEquals(Optional.empty(), b.transfer(7, "account1", "account1", 1));
        assertEquals(Optional.of(2500), b.transfer(8, "account1", "account2", 200));
    }

    @Order(2)
    @Test
    void level2RankingAndTieBreaks() {
        BankingSystemImpl b = new BankingSystemImpl();
        b.createAccount(1, "account1");
        b.createAccount(2, "account2");
        b.createAccount(3, "account3");
        b.deposit(4, "account1", 2000);
        b.deposit(5, "account2", 3000);
        b.deposit(6, "account3", 4000);
        assertEquals(List.of("account1(0)", "account2(0)", "account3(0)"),
                b.topSpenders(7, 3));
        b.transfer(8, "account3", "account2", 500);
        b.transfer(9, "account3", "account1", 1000);
        b.transfer(10, "account1", "account2", 2500);
        assertEquals(List.of("account1(2500)", "account3(1500)", "account2(0)"),
                b.topSpenders(11, 3));
    }

    @Order(3)
    @Test
    void level3PaymentAndCashback() {
        BankingSystemImpl b = new BankingSystemImpl();
        b.createAccount(1, "a");
        b.deposit(2, "a", 1000);
        assertEquals(Optional.of("payment1"), b.pay(3, "a", 500));
        assertEquals(Optional.of("IN_PROGRESS"),
                b.getPaymentStatus(4, "a", "payment1"));
        assertEquals(Optional.of("CASHBACK_RECEIVED"),
                b.getPaymentStatus(86400003, "a", "payment1"));
        assertEquals(Optional.of(510), b.getBalance(86400004, "a", 86400003));
    }

    @Order(4)
    @Test
    void level4MergeMovesPaymentOwnership() {
        BankingSystemImpl b = new BankingSystemImpl();
        b.createAccount(1, "a");
        b.createAccount(2, "b");
        b.deposit(3, "a", 100);
        b.deposit(4, "b", 500);
        b.pay(5, "b", 200);
        assertTrue(b.mergeAccounts(6, "a", "b"));
        assertEquals(Optional.empty(), b.deposit(7, "b", 1));
        assertEquals(Optional.of("IN_PROGRESS"),
                b.getPaymentStatus(8, "a", "payment1"));
        assertEquals(List.of("a(200)"), b.topSpenders(9, 1));
        assertEquals(Optional.of(400), b.getBalance(10, "a", 6));
    }
}