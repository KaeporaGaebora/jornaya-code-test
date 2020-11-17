package com.company.shortestpath;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShortPath {


    private AccountNode rootNode;

    private void setup(){
        //create transaction data.

        List<TransactionEntry> transactionEntryList = new ArrayList<>();
        transactionEntryList.add(new TransactionEntry(1, "AB", null));
        transactionEntryList.add(new TransactionEntry(2, "YZ", "AB"));
        transactionEntryList.add(new TransactionEntry(3, "MN", "YZ"));
        transactionEntryList.add(new TransactionEntry(3, "QR", "YZ"));

        transactionEntryList.add(new TransactionEntry(3, "HI", "YZ"));
        transactionEntryList.add(new TransactionEntry(4, "CD", "MN"));
        transactionEntryList.add(new TransactionEntry(5, "JK", "MN"));
        transactionEntryList.add(new TransactionEntry(5, "ST", "HI"));

        transactionEntryList.add(new TransactionEntry(6, "ST", "JK"));
        transactionEntryList.add(new TransactionEntry(7, "JK", "ST"));
        transactionEntryList.add(new TransactionEntry(8, "FG", "JK"));
        transactionEntryList.add(new TransactionEntry(8, "UV", "ST"));

        transactionEntryList.add(new TransactionEntry(9, "FG", "UV"));
        transactionEntryList.add(new TransactionEntry(10, "UV", "YZ"));



        //convert transaction data into linked graph
        rootNode = AccountNode.initialize(transactionEntryList);

    }


    public int shortestPath(AccountNode rootNode, String account, int asofTime) throws ShortPathException {

        //assumptions:
        // * There _are_ circular transactions
        //     this is important -
        // * The longest possible path is the total number of accounts (Based on above)
        //if the length exceeds total number of accounts, thats an easy way to detect circulars


        AccountNode target = AccountNode.findNode(account);
        if (target == null){
            throw new ShortPathException("Target Account Not Found");
        }


        Set<AccountNode> visited = new HashSet<>();

        int distance =  rootNode.pathLength(target, asofTime, visited);

        if (distance == -1) {
            throw new ShortPath.ShortPathException("No Path Found at Point in Time");
        }

        return distance;
    }






    public void tests(){
        setup();

//        harness(AccountNode.findNode("HI"), "UV", 9);

        harness(rootNode, "JK", 5);
        harness(rootNode, "ST", 5);
        harness(rootNode, "UV", 9);
        harness(rootNode, "FG", 9);
        harness(rootNode, "FG", 10);
        harness(rootNode, "FG", 3);
        harness(rootNode, "AZ", 9);
    }

    private void harness(AccountNode rootNode, String account, int time) {
        System.out.println("Start: " + rootNode + " Dest: " + account + " At Time: " + time);
        int result;
        try {
            result = shortestPath(rootNode, account, time);
            System.out.println("Total path length: " + result);
        } catch (ShortPathException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();

    }



    static class ShortPathException extends Exception {

        public ShortPathException(String errorMessage) {
            super(errorMessage);
        }
    }
}
