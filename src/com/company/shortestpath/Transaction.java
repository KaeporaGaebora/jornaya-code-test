package com.company.shortestpath;

public class Transaction {
    //contains timestamp, and destination TransactionData
    public int timestamp;
    public AccountNode accountTo;

    public Transaction(int timestamp, AccountNode accountTo) {
        this.timestamp = timestamp;
        this.accountTo = accountTo;
    }
}
