package com.jy.common.exception.reflect;

/**
 * create by jianglei on 2018/12/4
 */
public class InvocationTargetException extends InternalException {

    private  static String msg = "method invoke error , please check params !!";

    public InvocationTargetException() {
        super(msg);
    }

    public InvocationTargetException(String msg) {
        super(msg);
    }
}