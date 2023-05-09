package com.zoo.tiger.me.spr.fac;

/**
 * @author Tiger
 */
public enum CustomerTypeEnum {

    D(0,"默认"),
    P(1,"个人"),
    CP(2,"公司"),

    ;


    public int type;

    public String name;

    CustomerTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
}
