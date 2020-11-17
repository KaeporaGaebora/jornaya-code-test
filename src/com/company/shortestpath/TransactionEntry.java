package com.company.shortestpath;

public class TransactionEntry {


    private int timestamp;
    private String account_to;
    private String account_from;
    TransactionEntry(int timestamp, String account_to, String account_from){
        this.timestamp = timestamp;
        this.account_to = account_to;
        this.account_from = account_from;

    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getAccount_to() {
        return account_to;
    }

    public String getAccount_from() {
        return account_from;
    }

}
