package com.company.ebay;

import java.util.*;

/**
 * Progressive banking-system solution.
 * Level 3/4 API follows a common CodeSignal variant; verify
 * method signatures and business rules against your interface.
 * All timestamps are assumed nondecreasing.
 */
class BankingSystemImpl implements BankingSystem {
    private static final long CASHBACK_DELAY = 86_400_000L;

    private static final class Account {
        final String id;
        long balance;
        long outgoing;
        final TreeMap<Integer, Long> history = new TreeMap<>();

        Account(String id, int time) {
            this.id = id;
            history.put(time, 0L);
        }
    }

    private static final class Payment {
        final String id;
        String owner;
        final long due;
        final long cashback;
        boolean received;

        Payment(String id, String owner, long due, long cashback) {
            this.id = id;
            this.owner = owner;
            this.due = due;
            this.cashback = cashback;
        }
    }

    private final Map<String, Account> accounts = new HashMap<>();
    private final Map<String, Payment> payments = new HashMap<>();
    private final PriorityQueue<Payment> duePayments =
            new PriorityQueue<>(Comparator
                    .comparingLong((Payment p) -> p.due)
                    .thenComparing(p -> Integer.parseInt(p.id.substring(7))));
    private int paymentSequence = 0;

    public BankingSystemImpl() {
    }

    // Record the resulting balance after each successful change.
    private void record(Account a, int timestamp) {
        a.history.put(timestamp, a.balance);
    }

    private void processCashBacks(int timestamp) {
        while (!duePayments.isEmpty()
                && duePayments.peek().due <= (long) timestamp) {
            Payment p = duePayments.poll();
            if (p.received) continue;
            Account owner = accounts.get(p.owner);
            if (owner != null) {
                owner.balance += p.cashback;
                record(owner, (int) p.due);
            }
            p.received = true;
        }
    }

    @Override
    public boolean createAccount(int timestamp, String accountId) {
        processCashBacks(timestamp);
        if (accounts.containsKey(accountId)) return false;
        accounts.put(accountId, new Account(accountId, timestamp));
        return true;
    }

    @Override
    public Optional<Integer> deposit(
            int timestamp, String accountId, int amount) {
        processCashBacks(timestamp);
        Account a = accounts.get(accountId);
        if (a == null) return Optional.empty();
        a.balance += amount;
        record(a, timestamp);
        return Optional.of(Math.toIntExact(a.balance));
    }

    @Override
    public Optional<Integer> transfer(int timestamp,
                                      String sourceAccountId, String targetAccountId,
                                      int amount) {
        processCashBacks(timestamp);
        Account src = accounts.get(sourceAccountId);
        Account dst = accounts.get(targetAccountId);
        if (src == null || dst == null
                || sourceAccountId.equals(targetAccountId)
                || src.balance < amount) return Optional.empty();
        src.balance -= amount;
        dst.balance += amount;
        src.outgoing += amount;
        record(src, timestamp);
        record(dst, timestamp);
        return Optional.of(Math.toIntExact(src.balance));
    }

    @Override
    public List<String> topSpenders(int timestamp, int n) {
        processCashBacks(timestamp);
        List<Account> ranked = new ArrayList<>(accounts.values());

        ranked.sort(Comparator
                .comparingLong((Account a) -> a.outgoing).reversed()
                .thenComparing(a -> a.id));
        List<String> answer = new ArrayList<>();
        for (int i = 0; i < Math.min(n, ranked.size()); i++) {
            Account a = ranked.get(i);
            answer.add(a.id + "(" + a.outgoing + ")");
        }
        return answer;
    }

    // Assumed Level 3 API: pay, getPaymentStatus.
    public Optional<String> pay(int timestamp,
                                String accountId, int amount) {
        processCashBacks(timestamp);
        Account a = accounts.get(accountId);
        if (a == null || a.balance < amount) {
            return Optional.empty();
        }
        a.balance -= amount;
        a.outgoing += amount;
        record(a, timestamp);
        String id = "payment" + (++paymentSequence);
        long cashback = amount * 2L / 100L;
        Payment p = new Payment(id, accountId,
                (long) timestamp + CASHBACK_DELAY, cashback);
        payments.put(id, p);
        duePayments.add(p);
        return Optional.of(id);
    }

    public Optional<String> getPaymentStatus(int timestamp,
                                             String accountId, String paymentId) {
        processCashBacks(timestamp);
        Payment p = payments.get(paymentId);
        if (!accounts.containsKey(accountId) || p == null
                || !p.owner.equals(accountId)) {
            return Optional.empty();
        }
        return Optional.of(p.received
                ? "CASHBACK_RECEIVED" : "IN_PROGRESS");
    }

    // Assumed Level 4 API: mergeAccounts, getBalance.
    public boolean mergeAccounts(int timestamp,
                                 String accountId1, String accountId2) {
        processCashBacks(timestamp);
        Account keep = accounts.get(accountId1);
        Account remove = accounts.get(accountId2);
        if (keep == null || remove == null
                || accountId1.equals(accountId2)) return false;
// Merge historical balance timelines. Before merge, each
// account retains its original balance; from merge onward
// the surviving account holds the sum.
        keep.balance += remove.balance;
        keep.outgoing += remove.outgoing;
        record(keep, timestamp);
        for (Payment p : payments.values()) {
            if (p.owner.equals(accountId2)) p.owner = accountId1;
        }
        accounts.remove(accountId2);
        return true;
    }

    public Optional<Integer> getBalance(int timestamp,
                                        String accountId, int timeAt) {
        processCashBacks(timestamp);
        Account a = accounts.get(accountId);
        if (a == null) return Optional.empty();
        Map.Entry<Integer, Long> entry = a.history.floorEntry(timeAt);
        return entry == null ? Optional.empty()
                : Optional.of(Math.toIntExact(entry.getValue()));
    }
}