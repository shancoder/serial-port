package com.example.serialport.test;

import com.example.serialport.portdata.AllData;

public class Init {

    public static volatile AllData allData = new AllData();

    public static void main(String[] args) {

        Thread t1 = new Thread(new WorkThreadCowNum());
        Thread t2 = new Thread(new WorkThreadWeighingScale());

        t1.start();
        t2.start();
    }
}
