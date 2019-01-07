package com.jy.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 集合处理类
 * create by jianglei on 2018/12/29
 */
public class DataHandUtils {

    /**
    * 功能描述:切割list
    * @param:[list, size(每组个数)]
    * @return:java.util.List<java.util.List<T>>
    * @Date: 2018/12/29
    */
    public static <T> List<List<T>> splitList(List<T> list, Integer size){
        List<List<T>> groupList = new ArrayList<>();
        Stream.iterate(0, n-> n+1).limit(size).forEach(a ->{
            groupList.add(list.stream().skip(a*size).limit(size).collect(Collectors.toList()));
        });
        return groupList;
    }

}