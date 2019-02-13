package com.jy.common.exception.reflect;

import com.jy.common.exception.GenericException;

/**
 * create by jianglei on 2018/12/12
 */
public class NoSuchFieldException extends GenericException {

    private  static String msg = "not find field !!";

    public NoSuchFieldException() {
        super(msg);
    }

    public NoSuchFieldException(String msg) {
        super(msg);
    }

}