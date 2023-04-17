package com.zoo.tiger.me.bean;

import lombok.Data;

/**
 * @author Tiger
 */
@Data
public class ResponseBean<T> {

    private int code;

    private T data;

    private String msg;

    public static ResponseBean success(){
        ResponseBean<Object> success = new ResponseBean<>(new Object());
        success.code = 0 ;
        success.data = null;
        success.msg = "success";
        return success;
    }

    public ResponseBean(T data) {
        this.data = data;
    }
}
