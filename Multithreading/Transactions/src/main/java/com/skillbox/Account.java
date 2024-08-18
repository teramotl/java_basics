package com.skillbox;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {

    private long money;
    private String accNumber;
    private final Lock lock = new ReentrantLock();
    private boolean isBlocked = false;

    public Account(String accNumber, long initialMoney) {
        this.accNumber = accNumber;
        this.money = initialMoney;
    }

    public long getMoney() {
        lock.lock();
        try {
            return money;
        } finally {
            lock.unlock();
        }
    }

    public void setMoney(long money) {
        lock.lock();
        try {
            if (!isBlocked) {
                this.money = money;
            }
        } finally {
            lock.unlock();
        }
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void block() {
        lock.lock();
        try {
            isBlocked = true;
        } finally {
            lock.unlock();
        }
    }

    public void unblock() {
        lock.lock();
        try {
            isBlocked = false;
        } finally {
            lock.unlock();
        }
    }

    public Lock getLock() {
        return lock;
    }
}
