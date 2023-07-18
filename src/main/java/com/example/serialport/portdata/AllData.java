package com.example.serialport.portdata;

import com.example.serialport.send.TcpClientExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 包含编号和体重数据
 */
public class AllData {

    private static final Logger logger =  LoggerFactory.getLogger(AllData.class);

    private volatile CowNum cowNum;
    private volatile WeighingScale weighingScale;

    /**
     * 发送数据
     */
    public void sendData() {
        if (cowNum == null || weighingScale == null) {
            logger.error("数据不全，无法发送 cowNum={}, weighingScale={}", cowNum, weighingScale);
            return ;
        }
        TcpClientExample.send(this);
        logger.info("开发发送数据 cowNum={}, weightingScale={}", cowNum.hexToDecimal(), weighingScale.hexToDecimal());
        // 清空数据
        this.cleanData();

    }

    /**
     * 提交给接口时，需要的数据格式
     * @return
     */
    public String getStrData() {
        String ret = "AA55" + weighingScale.hexString() + cowNum.hexString() + "55AA";
        logger.info("组装数据 ret={}", ret);
        return ret;
    }

    /**
     * 清空数据
     */
    public void cleanData() {
        this.setWeighingScale(null);
        this.setCowNum(null);
        logger.info("清除数据，等待下一轮数据");
    }

    public void checkData() {
        System.out.println("查看数据");
        if (cowNum != null) {
            System.out.println("cowNum=" + cowNum.hexToDecimal());
        }
        if (weighingScale != null) {
            System.out.println("weithingScale=" + weighingScale.hexToDecimal());
        }
    }

    public CowNum getCowNum() {
        return cowNum;
    }

    public void setCowNum(CowNum cowNum) {
        this.cowNum = cowNum;
    }

    public WeighingScale getWeighingScale() {
        return weighingScale;
    }

    public void setWeighingScale(WeighingScale weighingScale) {
        this.weighingScale = weighingScale;
    }



}
