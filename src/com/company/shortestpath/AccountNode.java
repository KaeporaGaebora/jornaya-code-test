package com.company.shortestpath;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
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
                accounts.add(rootNode);
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


        for (AccountNode a: accounts) {
            if (a.getName().equals(account)) {
                return a;
            }
        }



        if (createIfNotFound) {
            AccountNode a = new AccountNode(account);
            accounts.add(a);
            return a;
        }

        return null;
    }





    //each object
    private Set<Transaction> transactions = new HashSet<>();
    private String accountName;

    public static AccountNode getRootNode() {
        return rootNode;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public String getName() {
        return accountName;
    }

    public AccountNode(String account) {
        this.accountName = account;
    }


    private void addTransaction(int timestamp, AccountNode accountTo){
        transactions.add(new Transaction(timestamp, accountTo));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountNode that = (AccountNode) o;
        return Objects.equals(accountName, that.accountName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountName);
    }

    @Override
    public String toString(){
        return this.getName();
    }



    public int pathLength(AccountNode destination, int asofTime, Set<AccountNode> visited) throws ShortPath.ShortPathException {
        //recursive, traverses graph

        //if no path found, returns -1
        //if path found, returns distance


        // keep track of nodes visited
        visited.add(this);


        //follow each leg

        //if you found the goal with a distance of 1, short circuit out
        //otherwise return the shortest

        int shortest = -1; //default value for path not found

        for (Transaction t : this.getTransactions()) {
            //must be within constraint
            if (t.timestamp <= asofTime) {

                AccountNode to = t.accountTo;

                //short-circuit: if we found a distance of 1, no need to keep searching
                if (to.equals(destination)) {
                    System.out.println(" ("+destination+","+asofTime+") " + this + " to " + to + " returned: 1");

                    return 1;
                }

                //check if we visited this node already
                if (visited.contains(to)) {
                    // don't bother checking this node again
                    //consider as if it was a dead end
                    System.out.println("repeat node visited");
                    continue;
                }

                //every new path gets its own history
                Set<AccountNode> newVisted = new HashSet<>(visited);

                int dist = to.pathLength(destination, asofTime, newVisted);
                System.out.println(" ("+destination+","+asofTime+") " + this + " to " + to + " returned: " + dist);

                if (dist == -1) {
                    //dead end
                    continue;
                }



                if (dist < shortest || shortest == -1) {
                    //found a path
                    shortest = dist + 1;
                }



            }
        }


        return shortest;
    }


}
