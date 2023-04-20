package com.zoo.tiger.me.controller;


import com.alibaba.fastjson2.JSON;
import com.zoo.tiger.me.bean.ResponseBean;
import com.zoo.tiger.me.mapper.SysUserMapper;
import com.zoo.tiger.me.model.SysUser;
import com.zoo.tiger.me.rabbitMQ.RabbitMQHelper;
import com.zoo.tiger.me.redis.jedis.JedisClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 示例控制器
 */
@RestController
@RestControllerAdvice
@RequestMapping(value = "/demo")
public class DemoController {

    private static Logger logger = LoggerFactory.getLogger(DemoController.class);

    private SysUserMapper mapper;

    public DemoController(SysUserMapper mapper) {
        this.mapper = mapper;
    }

    @Resource
    private JedisClient jedisClient;

    @Resource
    private RabbitMQHelper rabbitMQHelper;

    /**
     * 示例方法
     * @return
     */
    @GetMapping("/getUser")
    public ResponseBean<?> getUser() {
        rabbitMQHelper.sendMessageWithEmptyRoutingKey("abd","hello world");
        // 测试全局异常
        // throw new BizException(1,"zero ...");
        String cache = (String) jedisClient.get("1339550467939639299");
        if(!StringUtils.isEmpty(cache)) {
            logger.info("cache={}", cache);
            return new ResponseBean<>(JSON.parseObject(cache, SysUser.class));
        }
        SysUser sysUser = mapper.selectByPrimaryKey(1339550467939639299L);
        if(!Objects.isNull(sysUser)) {
            jedisClient.setEx("1339550467939639299", 10, JSON.toJSONString(sysUser));
        }
        logger.info("sysUser={}", JSON.toJSONString(sysUser));
        return new ResponseBean<>(sysUser);
    }
    /**
     * 示例方法
     * @return
     */
    @GetMapping("/getUser1")
    public ResponseBean<?> getUser1(@RequestBody SysUser req) {
        // 测试全局异常
        // throw new BizException(1,"zero ...");
        String cache = (String) jedisClient.get(String.valueOf(req.getUser_id()));
        if(!StringUtils.isEmpty(cache)) {
            logger.info("cache={}", cache);
            return new ResponseBean<>(JSON.parseObject(cache, SysUser.class));
        }
        SysUser sysUser = mapper.selectByPrimaryKey(req.getUser_id());
        if(!Objects.isNull(sysUser)) {
            jedisClient.setEx(String.valueOf(req.getUser_id()), 10, JSON.toJSONString(sysUser));
        }
        logger.info("sysUser={}", JSON.toJSONString(sysUser));
        return new ResponseBean<>(sysUser);
    }

}
