package com.jy.common.exception.reflect;

import com.jy.common.exception.GenericException;

/**
 * create by jianglei on 2018/12/4
 */
public class NoSuchMethodException extends GenericException {

    private  static String msg = "not find method !!";

    public NoSuchMethodException() {
        super(msg);
    }

    public NoSuchMethodException(String msg) {
        super(msg);
    }
}