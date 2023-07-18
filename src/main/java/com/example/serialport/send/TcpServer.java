package com.example.serialport.send;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    private static final Logger logger =  LoggerFactory.getLogger(TcpServer.class);

    public static void main(String[] args) throws Exception{
        int serverPort = 8091; // 服务器端口号
        ServerSocket serverSocket = new ServerSocket(serverPort);
        Socket clientSocket = serverSocket.accept();
        InputStream inputStream = clientSocket.getInputStream();
        OutputStream outputStream = clientSocket.getOutputStream();
        while (true) {
            // 接收数据
            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);
            String request = new String(buffer, 0, bytesRead);
            logger.info("服务端接收到数据 body={}", request);
        }
    }
}
