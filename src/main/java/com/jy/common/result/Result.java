package com.jy.common.result;


import com.jy.common.exception.BusinessException;

/**
 * 统一响应
 * Created by jianglei on 18-1-6.
 */
public class Result {

    private int code;//200为正常，其它为相关业务报错
    private String msg;//对应的错误信息,200为ok
    private Object body;//返回的业务对象

    public static Result ok(Object body){
        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");
        result.setBody(body);
        return result;
    }

    public static Result error(BusinessException e){
        Result result = new Result();
        result.setCode(e.getCode());
        result.setMsg(e.getMsg());
        return result;
    }

    public static Result error(int code){
        Result result = new Result();
        result.setCode(code);
        return result;
    }

    public static Result error(int code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public Result msg(String msg){
        this.setMsg(msg);
        return this;
    }

    public Result body(Object body){
        this.setBody(body);
        return this;
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

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
