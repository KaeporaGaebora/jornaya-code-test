package com.company.shortestpath;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TransactionData {

    //each instance of this object is a node in the graph

    //traversal may involve hitting the same object multiple times (not optimized)


    private static TransactionData rootNode;


    public static TransactionData initialize(List<TransactionEntry> transactionEntryList) {

        for (TransactionEntry entry : transactionEntryList) {
            //assume the only node with a null "from" is the root node,
            if (entry.getAccount_from() == null) {
                //create root node
                rootNode = new TransactionData(entry.getAccount_to());
            } else {
                TransactionData from = findOrCreate(entry.getAccount_from());
                TransactionData to = findOrCreate(entry.getAccount_to());
                from.addTransaction(entry.getTimestamp(), to);
            }
        }



        //return the root node;
        return rootNode;
    }

    public static TransactionData findNode(String account) {
        return find(account, false);
    }

    private static TransactionData findOrCreate(String account) {
        return find(account, true);
    }

    private static TransactionData find(String account, boolean createIfNotFound) {
        //returns node matching account name

        TransactionData result = null;


        //traverse graph, starting with rootnode

        //TODO






        if (createIfNotFound && result == null) {
            return new TransactionData(account);
        }

        return result;
    }




    //
    class Transaction {
        //contains timestamp, and destination TransactionData
        public int timestamp;
        public TransactionData accountTo;

        public Transaction(int timestamp, TransactionData accountTo) {
            this.timestamp = timestamp;
            this.accountTo = accountTo;
        }
    }


    //each object
    private Set<Transaction> transactions = new HashSet<>();
    private String account;


    public TransactionData(String account) {
        this.account = account;
    }


    private void addTransaction(int timestamp, TransactionData accountTo){
        transactions.add(new Transaction(timestamp, accountTo));
    }








}
