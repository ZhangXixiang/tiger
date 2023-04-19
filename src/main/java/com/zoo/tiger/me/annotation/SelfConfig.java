package com.zoo.tiger.me.annotation;

/**
 * @author Tiger
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SelfConfig {

    /**
     * 配置属性 的前缀
     */
    String prefix();
}