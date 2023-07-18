package com.example.serialport.send;

import com.example.serialport.portdata.AllData;
import com.example.serialport.portdata.CowNum;
import com.example.serialport.portdata.WeighingScale;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class TcpClientExample {

    private static final Logger logger =  LoggerFactory.getLogger(TcpClientExample.class);

    public static Socket socket;

    public static void main(String[] args) {
        AllData allData = new AllData();
        allData.setCowNum(new CowNum("AA 55 00 2D 00 00 07 D5 FF FF FF FE 00 F1 00 00 00 6B 00 33 05 00 00 00 00 00 00 00 00 00 00 00 10 6A 66 BA 63 2B 00 B7 59 00 FA 44 04 69 0F AA 81"));
        allData.setWeighingScale(new WeighingScale("AA 55 30 30 36 35 2E 33 30 19 11 23 16 38 49 55 AA"));
        allData.getWeighingScale().hexToDecimal();
        allData.getCowNum().hexToDecimal();

        while (true) {
            send(allData);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    /**
     * 发送数据
     * @param params
     * @return
     */
    public static String send(AllData params) {
        String host = "210.12.220.61";
//        String host = "127.0.0.1";
        try {
            BufferedWriter bw = null;
            if (socket == null || socket.isClosed() || !socket.isConnected()) {
                socket = new Socket(host, 8091);
                logger.info("判断scoket是否已连接={}", socket.isConnected());
                logger.info("启动socket客户端...");
            }
            if (bw == null) {
                OutputStream os = socket.getOutputStream();
                bw = new BufferedWriter(new OutputStreamWriter(os));
            }

            //向服务器端发送一条消息
            bw.write(params.getStrData());
            bw.flush();
            logger.info("发送数据成功 data={}", params.getStrData());
        } catch (UnknownHostException e) {
            e.printStackTrace();
            logger.error("发送数据失败 error=", e);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("发送数据失败 error=", e);
        }
        return null;
    }

    /**
     * 关闭连接
     */
    public static void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

