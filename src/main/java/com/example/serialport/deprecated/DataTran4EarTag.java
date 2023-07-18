package com.example.serialport.deprecated;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 电子耳标数据转换
 */
public class DataTran4EarTag {

    private static final Logger logger =  LoggerFactory.getLogger(DataTran4EarTag.class);

    /**
     * 原始字符串
     * 01032400000000270600000000000128F64286000283B500035D8B0002000100010000000200038C0E
     */
    public String dataString ;




    /**
     * 获取工厂类型
     * 第77个字符
     * @return
     */
    public FactoryType getFactoryType() {
        Integer ret = Integer.valueOf(dataString.charAt(77));
        logger.info("获取到公司类型为={}", ret);
        return FactoryType.findType(ret);
    }

    /**
     * 获取耳标的字符串
     * 4~15节，即7~30的字符
     * @return
     */
    public String getEarTagHexString() {
        String ret = dataString.substring(6, 30);
        logger.info("获取到电子耳标={}", ret);
        return ret;
    }

    /**
     * 获取电子耳标的十进制字符串
     * @return
     */
    public String getEarTagDec() throws Exception {
        String hexStr = this.getEarTagHexString();
        String decStr ;
        FactoryType factoryType = this.getFactoryType();
        switch (factoryType) {
            case GaiLiGe:
                decStr = this.hexToDecimal0(hexStr);
                break;
            case AnLeFu:
                case JianYongKeJi:
                    decStr = this.hexToDecimal1A3(hexStr);
                break;
            case JinRuiMing:
                decStr = hexStr;
            default:
                throw new Exception("没有获取到工厂类型");

        }
        logger.info("工厂类型={},转化为十进制={},原十六进制={}", factoryType.desc, decStr, hexStr);
        return decStr;
    }


    /**
     *
     * 00--盖丽阁
     * 需要把数值进行 16 进制转换最后获得耳标，示例如下：27 06 00 00 00 00 00 01 ，
     * 转换后为“9990 0000 0000 001"（注：最后 2 个字节取 后 3 位数）
     * @param hex
     * @return
     */
    public String hexToDecimal0(String hex) {
        // 补足16位，不足的话在左侧补零
        hex = String.format("%16s", hex).replace(' ', '0');

        // 将每4位十六进制数转换为十进制数
        StringBuilder decimalBuilder = new StringBuilder();
        for (int i = 0; i < hex.length(); i += 4) {
            String hexSubstring = hex.substring(i, i + 4);
            int decimal = Integer.parseInt(hexSubstring, 16);
            if (i == hex.length() - 4) {
                decimalBuilder.append(String.format("%03d", decimal));
            } else {
                decimalBuilder.append(String.format("%04d", decimal));
            }
            decimalBuilder.append(" ");
        }

        // 去除最后一个空格，并返回十进制结果
        return decimalBuilder.toString().trim();
    }

    /**
     * 01--安乐福、03--健永科技
     * 需要把数值进行 16 进制转换最后获得耳标， 示例如下：27 06 00 00 00 00 00 01， 转换后为“9990 0000 0000 0001" 。
     * @param hex
     * @return
     */
    public String hexToDecimal1A3(String hex) {
        // 补足16位，不足的话在左侧补零
        hex = String.format("%16s", hex).replace(' ', '0');

        // 将每4位十六进制数转换为十进制数
        StringBuilder decimalBuilder = new StringBuilder();
        for (int i = 0; i < hex.length(); i += 4) {
            String hexSubstring = hex.substring(i, i + 4);
            int decimal = Integer.parseInt(hexSubstring, 16);
            decimalBuilder.append(String.format("%04d", decimal));
            decimalBuilder.append(" ");
        }

        // 去除最后一个空格，并返回十进制结果
        return decimalBuilder.toString().trim();
    }




}
