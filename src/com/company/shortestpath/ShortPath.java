package com.company.shortestpath;

import java.util.ArrayList;
import java.util.List;

public class ShortPath {


    private TransactionData txData;

    private void setup(){
        //create transaction data.

        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(1, "AB", null));
        transactionList.add(new Transaction(2, "YZ", "AB"));
        transactionList.add(new Transaction(3, "MN", "YZ"));
        transactionList.add(new Transaction(3, "QR", "YZ"));

        transactionList.add(new Transaction(3, "HI", "YZ"));
        transactionList.add(new Transaction(4, "CD", "MN"));
        transactionList.add(new Transaction(5, "JK", "MN"));
        transactionList.add(new Transaction(5, "ST", "HI"));

        transactionList.add(new Transaction(6, "ST", "JK"));
        transactionList.add(new Transaction(7, "JK", "ST"));
        transactionList.add(new Transaction(8, "FG", "JK"));
        transactionList.add(new Transaction(8, "UV", "ST"));

        transactionList.add(new Transaction(9, "FG", "UV"));
        transactionList.add(new Transaction(10, "UV", "YZ"));



        //convert transaction data into linked graph
        txData = new TransactionData(transactionList);

    }


    int shortestPath(TransactionData data, String account, int time) throws ShortPathException {

        //assumptions:
        // * There are no circular transactions
        //     this is important - the problem would be a lot harder if there were.
        // * The longest possible path is the total number of accounts (Based on above)




        //follow each leg

        //if you found the goal with a distance of 1, short circuit out
        //otherwise return the shortest


        throw new ShortPathException("No Path Found at Point in Time");
//        throw new RecencyException("Target Account Not Found");



//        return 0;
    }



    public void tests(){
        setup();
        harness(txData, "JK", 4);
        harness(txData, "ST", 5);
        harness(txData, "UV", 9);
        harness(txData, "FG", 9);
        harness(txData, "FG", 10);
        harness(txData, "FG", 3);
        harness(txData, "AZ", 9);
    }

    private void harness(TransactionData data, String account, int time) {
        System.out.println("Testing: To: " + account + " At Time: " + time);
        int result;
        try {
            result = shortestPath(txData, "FG", 9);
            System.out.println(result);
        } catch (ShortPathException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();

    }



    class ShortPathException extends Exception {

        public ShortPathException(String errorMessage) {
            super(errorMessage);
        }
    }
}
