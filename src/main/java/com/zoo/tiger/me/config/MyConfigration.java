package com.zoo.tiger.me.config;

import com.zoo.tiger.me.model.SysUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Tiger
 */
// @Configuration用来声明一个配置类，然后使用 @Bean 注解，用于声明一个bean，将其加入到Spring容器中。
@Configuration
public class MyConfigration {

    @Bean(name = "user")
    public SysUser user() {
        SysUser sysUser = new SysUser();
        sysUser.setNick_name("tiger");
        return sysUser;
    }
}
