package com.jy.common.exception.reflect;

/**
 * create by jianglei on 2018/12/4
 */
public class IllegalAccessException extends InternalException {

    private  static String msg = "set or get a field, or invoke a method error , not get private !!";

    public IllegalAccessException() {
        super(msg);
    }

    public IllegalAccessException(String msg) {
        super(msg);
    }
}