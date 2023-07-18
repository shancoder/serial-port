package com.example.serialport.deprecated;


public enum FactoryType {

    GaiLiGe(0, "盖丽阁"),
    AnLeFu(1, "安乐福"),
    JinRuiMing(2, "金瑞铭"),
    JianYongKeJi(3, "健永科技"),
;
    public int code;
    public String desc;

    FactoryType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据数字返回工厂类型枚举
     * @param code
     * @return
     */
    public static FactoryType findType(int code) {
        for (FactoryType type : FactoryType.values()) {
            if (type.code == code) {
                return type;
            }
        }
        return null;
    }
}
