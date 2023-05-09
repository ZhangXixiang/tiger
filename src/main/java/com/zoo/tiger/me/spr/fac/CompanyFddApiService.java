package com.zoo.tiger.me.spr.fac;

import org.springframework.stereotype.Service;

/**
 * @author Tiger
 */
@Service
public class CompanyFddApiService extends AbstractFddApiService {

    @Override
    public Integer getPersonType() {
        return CustomerTypeEnum.CP.type;
    }

    @Override
    public String getInfo() {
        return "company";
    }
}
