package com.zoo.tiger.me.exception;

import com.zoo.tiger.me.bean.ResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Tiger
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = BizException.class)
    public ResponseBean handle(BizException e) {
        // 无效授权不打印，太多了
        logger.error("全局异常: {} {}", e.getCode(), e.getMsg());
        ResponseBean responseBean = new ResponseBean(new Object());
        responseBean.setCode(e.getCode());
        responseBean.setMsg(e.getMsg());
        return responseBean;
    }


}
