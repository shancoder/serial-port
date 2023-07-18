package com.example.serialport.listener;

import com.example.serialport.other.PortInit;
import com.example.serialport.other.SerialPortUtil;
import com.example.serialport.portdata.AllData;
import com.example.serialport.portdata.CowNum;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * 编号监听器
 */
public class CowNumListener implements SerialPortEventListener {

    private static final Logger logger =  LoggerFactory.getLogger(CowNumListener.class);

    @Override
    public void serialEvent(SerialPortEvent event) {
        if (event.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            SerialPort serialPort = (SerialPort) event.getSource();
            try {
                byte[] bytes = SerialPortUtil.getSerialPortUtil().readFromPort(serialPort);
                StringBuilder sb1 = new StringBuilder();
                for (byte b : bytes) {
                    String hexString = String.format("%02X", b & 0xFF);// 将 byte 转换为十六进制字符串
                    sb1.append(hexString);
                }

                // 处理接收到的数据
                String receivedData = new String(sb1);
                logger.info("CowNumListener 接收到的数据 receivedData={}", receivedData);
                // 将数据放置公共对象中
                CowNum cowNum = new CowNum(receivedData);
                InitListener.allData.setCowNum(cowNum);
                cowNum.hexString();
                cowNum.hexToDecimal();
            } catch (Exception ex) {
                logger.error("CowNumListener 接收数据错误 error=", ex);
            }
        } else {
            logger.error("CowNumListener 编号监听器非可用状态 event={}", event);
        }
    }
}
