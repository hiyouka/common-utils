package com.jy.common.exception.reflect;

import com.jy.common.exception.GenericException;

/**
 * create by jianglei on 2018/12/4
 */
public abstract class InternalException extends GenericException {

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