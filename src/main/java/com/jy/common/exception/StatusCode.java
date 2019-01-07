package com.jy.common.exception;

/**
 * create by jianglei on 2018/11/14
 * @since 1.0.0
 */
public enum  StatusCode {

    VARIABLE_ERROR(501,"参数无法解析"),
    LOGIN_FORBIDDEN(502,"登录拦截"),
    SOURCE_NOT_FOUND(503,"链接不存在"),
    ACCOUNT_ERROR(504,"用户不存在"),
    LOGIN_BADCREDENTIAL(505,"密码错误"),
    ACCOUNT_EXPIRED(506,"账户过期"),
    ACCOUNT_LOCKED(507,"账户被冻结"),
    ACCOUNT_DISABLED(508,"账户不可用"),
    LOGIN_CODEERROE(509,"验证码错误"),
    UN_LOGIN(510,"未登录"),
    LOGIN_EXPIRE(511,"登录过期");

    private Integer code;
    private String msg;

    StatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}