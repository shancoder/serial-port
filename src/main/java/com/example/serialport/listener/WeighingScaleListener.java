package com.example.serialport.listener;

import com.example.serialport.other.SerialPortUtil;
import com.example.serialport.portdata.WeighingScale;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * 体重监听器
 */
public class WeighingScaleListener implements SerialPortEventListener {

    private static final Logger logger =  LoggerFactory.getLogger(WeighingScaleListener.class);

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
                logger.info("WeighingScaleListener 接收到体重的信息={}", receivedData);
                InitListener.allData.setWeighingScale(new WeighingScale(receivedData));
                // 接收到体重之后触发发送数据组的逻辑
                InitListener.allData.sendData();
            } catch (Exception ex) {
                logger.error("WeighingScaleListener 接收体重数据错误 error=", ex);
            }
        } else {
            logger.error("WeighingScaleListener 监听器非可用状态 event={}", event);
        }
    }
}
