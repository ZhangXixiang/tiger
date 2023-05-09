package com.zoo.tiger.me.spr.fac;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Tiger
 */
@Component
public class FddApiFactory {


    private ConcurrentHashMap<Integer, InterfaceFddApi> apiMap = new ConcurrentHashMap<>(16);

    @Autowired
    public FddApiFactory(List<InterfaceFddApi> list) {
        list.forEach(item -> apiMap.put(item.getPersonType(), item));
    }

    public InterfaceFddApi getApi(int typeEnum) {
        return apiMap.get(typeEnum);
    }


}
