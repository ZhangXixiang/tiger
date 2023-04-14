package com.zoo.tiger.me.controller;


import com.alibaba.fastjson2.JSON;
import com.zoo.tiger.me.bean.ResponseBean;
import com.zoo.tiger.me.exception.BizException;
import com.zoo.tiger.me.mapper.SysUserMapper;
import com.zoo.tiger.me.model.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 示例控制器
 */
@RestController
@RestControllerAdvice
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
        throw new BizException(1,"zero ...");
        /*SysUser sysUser = mapper.selectByPrimaryKey(1339550467939639299L);
        logger.info("sysUser={}", JSON.toJSONString(sysUser));
        return new ResponseBean<>();*/
    }

}
