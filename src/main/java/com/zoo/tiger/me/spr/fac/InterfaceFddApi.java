package com.zoo.tiger.me.spr.fac;

/**
 * @author Tiger
 * 如果命名为IFddApi会被ibatis默认处理？？
 * Caused by: org.apache.ibatis.binding.BindingException: Invalid bound statement (not found): com.zoo.tiger.me.spr.fac.IFddApi.getType
 */
public interface InterfaceFddApi {

    Integer getPersonType();

    String getInfo();
}
