package com.zoo.tiger.me.controller;


import com.alibaba.fastjson2.JSON;
import com.zoo.tiger.me.bean.ResponseBean;
import com.zoo.tiger.me.mapper.SysUserMapper;
import com.zoo.tiger.me.model.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 示例控制器
 */
@RestController
@RequestMapping(value = "/demo")
public class DemoController {

    private static Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Resource
    private SysUserMapper mapper;

    /**
     * 示例方法
     * @return
     */
    @GetMapping("/getUser")
    public ResponseBean<?> getUser() {
        SysUser sysUser = mapper.selectByPrimaryKey(1339550467939639299L);
        logger.info("sysUser={}", JSON.toJSONString(sysUser));
        return new ResponseBean<>();
    }

}
