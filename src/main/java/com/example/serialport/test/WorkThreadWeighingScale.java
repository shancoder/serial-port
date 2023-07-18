package com.example.serialport.test;

import com.example.serialport.listener.InitListener;
import com.example.serialport.portdata.CowNum;
import com.example.serialport.portdata.WeighingScale;

public class WorkThreadWeighingScale implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        WeighingScale weighingScale = new WeighingScale("AA 55 30 30 36 35 2E 33 30 19 11 23 16 38 49 55 AA");
        InitListener.allData.setWeighingScale(weighingScale);
        System.out.println("设置完体重，发送内容");
        InitListener.allData.sendData();
        InitListener.allData.checkData();
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        InitListener.allData.setWeighingScale(weighingScale);
        System.out.println("设置完体重，发送内容");
        InitListener.allData.sendData();
        InitListener.allData.checkData();
    }

}
