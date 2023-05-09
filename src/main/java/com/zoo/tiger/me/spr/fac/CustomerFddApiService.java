package com.zoo.tiger.me.spr.fac;

import org.springframework.stereotype.Service;

/**
 * @author Tiger
 */
@Service
public class CustomerFddApiService extends AbstractFddApiService {

    @Override
    public Integer getPersonType() {
        return CustomerTypeEnum.P.type;
    }

    @Override
    public String getInfo() {
        return "customer";
    }
}
