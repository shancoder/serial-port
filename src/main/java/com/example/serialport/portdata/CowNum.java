package com.example.serialport.portdata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 牛的编号
 */
public class CowNum {

    private static final Logger logger =  LoggerFactory.getLogger(CowNum.class);

    /**
     * 原始字符串
     * AA 55 00 2D 00 00 07 D5 FF FF FF FE 00 F1 00 00 00 6B 00 33 05 00 00 00 00 00 00 00 00 00 00 00 10 6A 66 BA 63 2B 00 B7 59 00 FA 44 04 69 0F AA 81
     */
    public String dataString ;

    public CowNum(String dataString) {
        this.dataString = dataString;
    }

    /**
     * 获取十六进制的表示
     * @return
     */
    public String hexString() {
        dataString = dataString.replaceAll(" ", "");
        String hexString = dataString.substring(28, 36);
        logger.info("获取牛的编码，十六进制={}，原始数据={}", hexString, dataString);
        return hexString;
    }

    /**
     * 获取十进制的表示
     * @return
     */
    public double hexToDecimal() {
        String hexString = this.hexString();
        String reversedHex = new StringBuilder(hexString).reverse().toString();
        long decimal = 0;
        long multiplier = 1;
        for (int i = 0; i < reversedHex.length(); i++) {
            char c = reversedHex.charAt(i);
            int digit = Character.digit(c, 16);
            decimal += digit * multiplier;
            multiplier *= 16;
        }
        logger.info("获取牛的编码，十进制={}, 十六进制={}", decimal, hexString);
        return decimal;
    }


}
