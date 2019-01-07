package com.jy.common.exception.reflect;

/**
 * create by jianglei on 2018/12/4
 */
public class NoSuchMethodException extends InternalException {

    private  static String msg = "not find method !!";

    public NoSuchMethodException() {
        super(msg);
    }

    public NoSuchMethodException(String msg) {
        super(msg);
    }
}