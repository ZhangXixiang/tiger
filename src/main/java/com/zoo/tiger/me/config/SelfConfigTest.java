package com.zoo.tiger.me.config;

/**
 * @author Tiger
 */

import com.zoo.tiger.me.annotation.SelfConfig;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
// @Configuration 加上这个注解后返回会交给springcglib处理
//
@SelfConfig(prefix = "self")
@Data
@Slf4j
public class SelfConfigTest implements InitializingBean, DisposableBean {

    private String username;

    private String password;

    @PostConstruct
    private void init() {
      log.info("username={}", username);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("after");
    }

    @Override
    public void destroy() throws Exception {
        log.info("destory");
    }
}