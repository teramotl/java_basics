package com.skillbox;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Bank {

    public final Map<String, Account> accounts = new HashMap<>();
    private final Random random = new Random();

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    public void transfer(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException {
        Account fromAccount = accounts.get(fromAccountNum);
        Account toAccount = accounts.get(toAccountNum);

        if (fromAccount == null || toAccount == null) {
            throw new IllegalArgumentException("Invalid account numbers.");
        }

        fromAccount.getLock().lock();
        toAccount.getLock().lock();

        try {
            if (fromAccount.isBlocked() || toAccount.isBlocked()) {
                throw new IllegalStateException("One or both accounts are blocked.");
            }

            if (fromAccount.getMoney() < amount) {
                throw new IllegalArgumentException("Insufficient funds.");
            }

            fromAccount.setMoney(fromAccount.getMoney() - amount);
            toAccount.setMoney(toAccount.getMoney() + amount);

            if (amount > 50000 && isFraud(fromAccountNum, toAccountNum, amount)) {
                fromAccount.block();
                toAccount.block();
            }

        } finally {
            fromAccount.getLock().unlock();
            toAccount.getLock().unlock();
        }
    }

    public long getBalance(String accountNum) {
        Account account = accounts.get(accountNum);
        if (account == null) {
            throw new IllegalArgumentException("Invalid account number.");
        }
        return account.getMoney();
    }

    public long getSumAllAccounts() {
        return accounts.values().stream().mapToLong(Account::getMoney).sum();
    }
}
