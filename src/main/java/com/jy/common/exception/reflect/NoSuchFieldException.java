package com.jy.common.exception.reflect;

/**
 * create by jianglei on 2018/12/12
 */
public class NoSuchFieldException extends InternalException {

    private  static String msg = "not find field !!";

    public NoSuchFieldException() {
        super(msg);
    }

    public NoSuchFieldException(String msg) {
        super(msg);
    }

}