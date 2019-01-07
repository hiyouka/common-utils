package com.jy.common.exception;

/**
 * 业务异常
 * 2018-01-08
 * @author wangyifan
 */
public abstract class BusinessException extends RuntimeException {
    private int code;
    private String msg;

    public BusinessException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
