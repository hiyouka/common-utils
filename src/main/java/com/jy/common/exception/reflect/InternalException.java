package com.jy.common.exception.reflect;

/**
 * create by jianglei on 2018/12/4
 */
public abstract class InternalException extends RuntimeException{

    private String msg;

    public InternalException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}