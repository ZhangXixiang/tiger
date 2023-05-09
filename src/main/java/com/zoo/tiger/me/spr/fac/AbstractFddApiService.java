package com.zoo.tiger.me.spr.fac;

/**
 * @author Tiger
 */
public class AbstractFddApiService implements InterfaceFddApi {


    @Override
    public Integer getPersonType() {
        return CustomerTypeEnum.D.type;
    }

    @Override
    public String getInfo() {
        return "default";
    }
}
