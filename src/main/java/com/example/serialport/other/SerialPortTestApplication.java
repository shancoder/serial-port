package com.example.serialport.other;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PreDestroy;


@SpringBootApplication
public class SerialPortTestApplication
{

    public static void main(String[] args)
    {

    }





    @PreDestroy
    public void destroy()
    {
        //关闭应用前 关闭端口
        SerialPortUtil serialPortUtil = SerialPortUtil.getSerialPortUtil();
        serialPortUtil.removeListener(PortInit.serialPort, new MyLister());
        serialPortUtil.closePort(PortInit.serialPort);
    }




}
