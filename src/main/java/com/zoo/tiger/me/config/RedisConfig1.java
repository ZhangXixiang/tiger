// package com.zoo.tiger.me.config;
//
// import lombok.Data;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.context.annotation.Configuration;
//
// import javax.annotation.PostConstruct;
//
// /**
//  * 配置文件
//  *
//  * @Tiger
//  */
// @Configuration
// @ConfigurationProperties(prefix = "redis")
// @Slf4j
// @Data
// public class RedisConfig1 {
//     private String host;
//     private Integer port;
//     private Integer maxActive;
//     private Integer maxWait;
//     private Integer minIdle;
//     private Integer maxIdle;
//     private String password;
//
//     @PostConstruct
//     private void init() {
//         log.info("ConfigurationProperties={}", host);
//     }
// }
