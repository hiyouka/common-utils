package com.jy.common.exception.reflect;

import com.jy.common.exception.GenericException;

/**
 * create by jianglei on 2018/12/4
 */
public class InvocationTargetException extends GenericException {

    private  static String msg = "method invoke error , please check params !!";

    public InvocationTargetException() {
        super(msg);
    }

    public InvocationTargetException(String msg) {
        super(msg);
    }
}