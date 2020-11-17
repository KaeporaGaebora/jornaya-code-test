package com.company;

import com.company.recency.Recency;
import com.company.shortestpath.ShortPath;

public class Main {

    public static void main(String[] args) {

        System.out.println("Recency:");
        Recency r = new Recency();
        r.runTest();

        System.out.println("ShortPath:");
        ShortPath sp = new ShortPath();
        sp.tests();



    }
}
