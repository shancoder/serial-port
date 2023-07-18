package com.example.serialport.test;

import com.example.serialport.listener.InitListener;
import com.example.serialport.portdata.CowNum;

public class WorkThreadCowNum implements Runnable {

    @Override
    public void run() {
        CowNum cowNum = new CowNum("AA 55 00 2D 00 00 07 D5 FF FF FF FE 00 F1 00 00 00 6B 00 33 05 00 00 00 00 00 00 00 00 00 00 00 10 6A 66 BA 63 2B 00 B7 59 00 FA 44 04 69 0F AA 81");
        InitListener.allData.setCowNum(cowNum);
        System.out.println("设置完编号，等待中");
        InitListener.allData.checkData();
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
