package com.company.shortestpath;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AccountNode {

    //each instance of this object is a node in the graph

    //traversal may involve hitting the same object multiple times (not optimized)


    private static Set<AccountNode> accounts = new HashSet<>();

    private static AccountNode rootNode;


    public static AccountNode initialize(List<TransactionEntry> transactionEntryList) {

        for (TransactionEntry entry : transactionEntryList) {
            //assume the only node with a null "from" is the root node,
            if (entry.getAccount_from() == null) {
                //create root node
                rootNode = new AccountNode(entry.getAccount_to());
            } else {
                AccountNode from = findOrCreate(entry.getAccount_from());
                AccountNode to = findOrCreate(entry.getAccount_to());
                from.addTransaction(entry.getTimestamp(), to);
            }
        }



        //return the root node;
        return rootNode;
    }

    public static AccountNode findNode(String account) {
        return find(account, false);
    }

    private static AccountNode findOrCreate(String account) {
        return find(account, true);
    }

    private static AccountNode find(String account, boolean createIfNotFound) {
        //returns node matching account name

        AccountNode result = null;


        //traverse graph, starting with rootnode

        //TODO






        if (createIfNotFound && result == null) {
            return new AccountNode(account);
        }

        return result;
    }

    public static AccountNode findAccount(String account) {
        //returns null if no account found
        return null;
    }




    //each object
    private Set<Transaction> transactions = new HashSet<>();
    private String account;

    public static AccountNode getRootNode() {
        return rootNode;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public String getAccount() {
        return account;
    }

    public AccountNode(String account) {
        this.account = account;
    }


    private void addTransaction(int timestamp, AccountNode accountTo){
        transactions.add(new Transaction(timestamp, accountTo));
    }








}
