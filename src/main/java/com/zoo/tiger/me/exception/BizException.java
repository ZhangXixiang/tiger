package com.zoo.tiger.me.exception;

import lombok.Data;

/**
 * 业务异常
 * @author Tiger
 */
@Data
public class BizException extends RuntimeException{

    private int code;

    private String msg;

    public BizException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
