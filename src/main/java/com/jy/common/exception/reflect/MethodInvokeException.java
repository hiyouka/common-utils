package com.jy.common.exception.reflect;

/**
 * create by jianglie on 2018/12/12
 */
public class MethodInvokeException extends InternalException {

    private  static String msg = "method invoke error !!";

    public MethodInvokeException(){
        super(msg);
    }

    public MethodInvokeException(String msg) {
        super(msg);
    }

}