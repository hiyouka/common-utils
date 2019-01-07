package com.jy.common.utils;

/**
 * 基础工具类 string处理
 * @author qiudongbiao
 *
 */
public class StringUtils{

    public static boolean isEmpty(String str){
        return str == null ? true : "".equals(str.trim());
    }

    public static boolean notEmpty(String str){
        return str == null ? false : !"".equals(str.trim());
    }

}
