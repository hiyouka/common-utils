package com.jy.common.utils;

/**
 * @author hiyouka
 * Date: 2019/1/28
 * @since JDK 1.8
 */
public class ObjectUtils {

    public static boolean isEmpty(Object... array) {
        return (array == null || array.length == 0);
    }

}