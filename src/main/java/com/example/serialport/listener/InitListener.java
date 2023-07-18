package com.example.serialport.listener;

import com.example.serialport.portdata.AllData;
import gnu.io.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Enumeration;
import java.util.TooManyListenersException;

public class InitListener {

    private static final Logger logger =  LoggerFactory.getLogger(InitListener.class);

    public SerialPort cowNumSerialPort;
    public SerialPort WeighingScaleSerialPort;

    /**
     * 静态变量保存编号和体重数据，使两个线程可以同时给此对象赋值
     */
    public static volatile AllData allData = new AllData();

    public InitListener() {
        super();
    }

    public void initialize() {
        try {

            // 获取可用串口列表
            Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier.getPortIdentifiers();

            // 遍历可用串口
            while (portEnum.hasMoreElements()) {
                CommPortIdentifier portIdentifier = portEnum.nextElement();
                logger.info("遍历所有串口，当前串口 portIdentifierName={}", portIdentifier.getName());

                // 根据串口名称打开串口1
                if (portIdentifier.getName().equals("COM1")) {
                    cowNumSerialPort = (SerialPort) portIdentifier.open("SerialPort1", 2000);
                    cowNumSerialPort.addEventListener(new CowNumListener());
                    cowNumSerialPort.notifyOnDataAvailable(true);
                    cowNumSerialPort.setSerialPortParams(57600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                    logger.info("初始化编码的串口完成 cowNumSerialPort");
                }

                // 根据串口名称打开串口2
                if (portIdentifier.getName().equals("COM2")) {
                    WeighingScaleSerialPort = (SerialPort) portIdentifier.open("SerialPort2", 2000);
                    WeighingScaleSerialPort.addEventListener(new WeighingScaleListener());
                    WeighingScaleSerialPort.notifyOnDataAvailable(true);
                    WeighingScaleSerialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                    logger.info("初始化体重的串口完成 WeighingScaleSerialPort");
                }
            }
        } catch (PortInUseException | UnsupportedCommOperationException | TooManyListenersException ex) {
            logger.error("InitListener 初始化串口出错 error=", ex);
        }
    }


    public static void main(String[] args) {
        InitListener listener = new InitListener();
        listener.initialize();

        // 程序保持运行，监听串口数据
        while (true) {
            // 这里可以添加其他逻辑处理
        }
    }
}
