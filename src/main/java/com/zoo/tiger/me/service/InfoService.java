package com.zoo.tiger.me.service;


import com.alibaba.fastjson2.JSON;
import com.zoo.tiger.me.model.People;

public class InfoService implements Info<People>{

    @Override
    public People getInfo(People para) {
        return para;
    }

    public static void main(String[] args) throws Exception {
        People people = new People(5,"helen");
        System.out.println(JSON.toJSONString(people.getAge()));
        System.out.println(People.class);
        System.out.println(people.getClass());
        System.out.println(Class.forName("com.zoo.tiger.me.model.People"));
    }


}
