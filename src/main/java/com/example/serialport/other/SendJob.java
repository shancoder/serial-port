package com.example.serialport.other;




import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author jiangwang
 * @Created by 2022/4/19 10:24
 * @Description: TODO
 */
@Component
@EnableScheduling
public class SendJob
{

    /**
     * 定时发送数据
     */
    // TODO
//    @Scheduled(initialDelay = 1000 * 10, fixedDelay = 1000 * 30)
    public void plcAnalytic()
    {
        String s = "{\"name\":\"111\",\"id\":\"2\"}";
        SerialPortUtil.getSerialPortUtil().sendToPort(PortInit.serialPort, s.getBytes());
    }
}


