package com.example.serialport.other;


import gnu.io.SerialPort;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PortInit implements ApplicationRunner {

    public static gnu.io.SerialPort serialPort = null;

    @Override
    public void run(ApplicationArguments args)
    {
        String portName = "COM3";
        //TestA();
        //查看所有串口
        SerialPortUtil serialPortUtil = SerialPortUtil.getSerialPortUtil();
//        ArrayList<String> port = serialPortUtil.findPort();
        // TODO
        ArrayList<String> port = null;
        System.out.println("发现全部串口：" + port);
        System.out.println("打开指定portName:" + portName);
        //打开该对应portName名字的串口
        PortInit.serialPort = serialPortUtil.openPort(
                portName,
                115200,
                SerialPort.DATABITS_8,
                SerialPort.PARITY_NONE,
                SerialPort.PARITY_ODD);
        //给对应的serialPort添加监听器
        serialPortUtil.addListener(PortInit.serialPort, new MyLister());
    }

}