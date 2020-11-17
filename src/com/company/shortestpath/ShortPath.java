package com.company.shortestpath;

import java.util.ArrayList;
import java.util.List;

public class ShortPath {


    private AccountNode txData;

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
        txData = AccountNode.initialize(transactionEntryList);

    }


    public int shortestPath(AccountNode rootNode, String account, int asofTime) throws ShortPathException {

        //assumptions:
        // * There are no circular transactions
        //     this is important - the problem would be a lot harder if there were.
        // * The longest possible path is the total number of accounts (Based on above)


        AccountNode target = AccountNode.findNode(account);
        if (target == null){
            throw new ShortPathException("Target Account Not Found");
        }




        int distance =  rootNode.pathLength(target, asofTime);

        if (distance == -1) {
            throw new ShortPath.ShortPathException("No Path Found at Point in Time");
        }

        return distance;
    }






    public void tests(){
        setup();
        harness(txData, "JK", 4);
//        harness(txData, "ST", 5);
//        harness(txData, "UV", 9);
//        harness(txData, "FG", 9);
//        harness(txData, "FG", 10);
//        harness(txData, "FG", 3);
//        harness(txData, "AZ", 9);
    }

    private void harness(AccountNode data, String account, int time) {
        System.out.println("Testing Dest: " + account + " At Time: " + time);
        int result;
        try {
            result = shortestPath(data, account, time);
            System.out.println(result);
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
