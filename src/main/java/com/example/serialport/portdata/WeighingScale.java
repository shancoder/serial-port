package com.example.serialport.portdata;

import com.example.serialport.deprecated.DataTran4EarTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 过道秤的数据，获取体重
 */
public class WeighingScale {

    private static final Logger logger =  LoggerFactory.getLogger(DataTran4EarTag.class);

    /**
     * 原始字符串
     * AA 55 30 30 36 35 2E 33 30 19 11 23 16 38 49 55 AA
     */
    public String dataString ;

    public WeighingScale(String dataString) {
        this.dataString = dataString;
    }

    /**
     * 返回原始十六进制
     * @return
     */
    public String hexString() {
        dataString = dataString.replaceAll(" ", "");
        String hexString = dataString.substring(4, 18);
        logger.info("获取体重十六进制={}, 原始数据={}", hexString, dataString);
        return hexString;
    }

    public double hexToDecimal() {
        String hexString = this.hexString();
        // 将十六进制字符串转换为字节数组
        byte[] bytes = new byte[hexString.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            String hex = hexString.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(hex, 16);
        }

        // 将字节数组转换为十进制浮点数
        String decimalString = new String(bytes);
        double decimal = Double.parseDouble(decimalString);
        logger.info("获取体重={}, 十六进制={}, 原始数据={}", decimal, hexString, dataString);
        return decimal;
    }

}
