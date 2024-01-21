package com.zoo.tiger.me.controller;


import com.alibaba.fastjson2.JSON;
import com.zoo.tiger.me.annotation.Log;
import com.zoo.tiger.me.bean.ResponseBean;
import com.zoo.tiger.me.mapper.SysUserMapper;
import com.zoo.tiger.me.model.SysUser;
import com.zoo.tiger.me.rabbitMQ.RabbitMQHelper;
import com.zoo.tiger.me.redis.jedis.JedisClient;
import com.zoo.tiger.me.spr.fac.FddApiFactory;
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

    @Resource(name = "jedisClient111")
    private JedisClient jedisClient;

    @Resource
    private RabbitMQHelper rabbitMQHelper;

    @Autowired
    private FddApiFactory factory;


    /**
     * 示例方法
     * @return
     */
    @GetMapping("/getUser")
    public ResponseBean<?> getUser() {
        // direct
        rabbitMQHelper.sendMessageWithEmptyRoutingKey("direct-abd","direct-abd-hello world");

        // rabbitMQHelper.sendMessageWithRoutingKey("direct-abd","direct-abd-hello world");
        // fanout
        // rabbitMQHelper.sendMessageWithEmptyRoutingKey("fanout-abd","fanout-hello world");
        // rabbitMQHelper.sendMessageWithRoutingKey("fanout-abd","fanout-hello world");
        // topic
        // rabbitMQHelper.sendMessageWithRoutingKey("topic-abd","topic-hello world");
        // 测试全局异常
        // throw new BizException(1,"zero ...");
        String cache = (String) jedisClient.get("1339550467939639299");
        if(!StringUtils.isEmpty(cache)) {
            logger.info("cache={}", cache);
            return new ResponseBean<>(JSON.parseObject(cache, SysUser.class));
        }
        SysUser sysUser = mapper.selectByPrimaryKey(1339550467939639299L);
//        PageHelper.startPage(1,1);
        if(!Objects.isNull(sysUser)) {
            jedisClient.setEx("1339550467939639299", 10, JSON.toJSONString(sysUser));
        }
        logger.info("sysUser={}", JSON.toJSONString(sysUser));
        return new ResponseBean<>(sysUser);
    }


    @Log(businessName = "========测试日志getInfo========")
    @GetMapping("/getInfo")
    public ResponseBean<?> getInfo(@RequestParam("type") int type) {
        ResponseBean res = ResponseBean.success();
        String info = factory.getApi(type).getInfo();
        res.setData(info);
        logger.info("info={}", info);
        return res;
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
