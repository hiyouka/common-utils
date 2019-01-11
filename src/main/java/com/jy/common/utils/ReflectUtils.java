
package com.jy.common.utils;


import com.jy.common.exception.reflect.MethodInvokeException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

import static com.jy.common.utils.ReflectUtils.MethodPrefix.GET;
import static com.jy.common.utils.ReflectUtils.MethodPrefix.SET;

/**
 * need JDK 1.8
 */
public class ReflectUtils {

    public static final String ALL = "allParam";

    public static final String IGNORE = "ignore";

    private static final String getPrefix = "get";
    private static final String setPrefix = "set";

    enum  MethodPrefix{
        GET,SET
    }

    /**
     * 功能描述:将指定对象集合属性转化为map集合
     * @param:[list, args]  exceptParam
     * @return:java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @since: 1.0.0
     * @Author:jianglei
     * @Date: 2018/9/5
     */
    public static <T> List<Map<String,Object>> getListForMap(List<T> list, String[] args, String expression, boolean isIgnoreParent){
        return list.stream().map(obj -> getForMap(obj,args,expression,isIgnoreParent)).collect(Collectors.toList());
    }

    /**
    * 功能描述:将对象某些属性转为map集合
    * @param:[obj, args, expression(表达式:IGNORE:去除选择的属性 ALl:所有属性),isGetParent(是否忽略父类属性)]
    * @return:java.util.Map<java.lang.String,java.lang.Object>
    * @Date: 2018/12/12
    */
    public static  <T> Map<String,Object> getForMap(T obj, String[] args, String expression, boolean isIgnoreParent){
        Map<String, Object> entity = new HashMap<>();
        Class<?> aClass = obj.getClass();
        List<String> params;
        if(expression.equals(ALL)){
            Field[] allField  = getAllField(aClass,isIgnoreParent,true);
            params = Arrays.stream(allField).map(Field::getName).collect(Collectors.toList());
        }else if(expression.equals(IGNORE)){
            List<String> collect = Arrays.stream(getAllField(aClass, isIgnoreParent, true)).map(Field::getName).collect(Collectors.toList());
            for(int i= 0 ; i<args.length; i++){
                if(collect.contains(args[i])){
                    collect.remove(args[i]);
                }
            }
            params = collect;
        }else{
            params = Arrays.asList(args);

        }
        params.forEach(arg -> {
            try {
                Method getMethod = getPropertyMethod(arg, aClass, GET);
                Object invoke = getMethod.invoke(obj);
                entity.put(arg, invoke);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new MethodInvokeException();
            }
        });
        return entity;
    }

    /**
     * 功能描述:拷贝对象(Map<String,Object> 或者 bean)属性
     * @param:[oldBean, newBean]
     * @return:T
     * @since: 1.0.0
     * @Author:jianglei
     * @Date: 2018/11/13
     */
    public static <T> void copyProperties(T oldBean, T newBean){
        List<String> fileds = new ArrayList<>();
        Class<?> newClazz = newBean.getClass();
        boolean isMap = newBean instanceof Map;
        if(oldBean instanceof Map){
            Set set = ((Map) oldBean).keySet();
            for(Object str : set){
                fileds.add(str+"");
            }
        }else {
            Class<?> oldClazz = oldBean.getClass();
            fileds = Arrays.stream(getAllField(oldClazz,true,true)).map(Field::getName).collect(Collectors.toList());
        }
        for (String field : fileds){
            Class<?> fieldType = null;
            if(!isMap){
                try {
                    fieldType = newClazz.getDeclaredField(field).getType();
                } catch (NoSuchFieldException e) {
                    continue;
                }
            }
            Object result = getResult(oldBean, field);
            if(result == null || !result.getClass().equals(fieldType)){
                continue;
            }
            setProperty(newBean,field,result);
        }
    }

   /**
   * 功能描述:获取类的所有属性
   * @param:[clazz, isIgnoreFather(是否忽略父类属性),onlyHasSetAndGet(只获取有set get方法的属性)]
   * @return:java.lang.reflect.Field[]
   * @Date: 2018/12/12
   */
    private static Field[] getAllField(Class<?> clazz,boolean isIgnoreFather,boolean onlyHasSetAndGet){
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null){
            Field[] fields = clazz.getDeclaredFields();
            if(onlyHasSetAndGet){
                for(Field field : fields){
                    try {
                        Method setMethod = getPropertyMethod(field.getName(), clazz, SET);
                        Method getMethod = getPropertyMethod(field.getName(), clazz, GET);
                        if(setMethod != null && getMethod != null){
                            fieldList.add(field);
                        }
                    }catch (Exception e){}
                }
            }else {
                fieldList.addAll(Arrays.asList(fields));
            }
            if(isIgnoreFather)
                clazz = null;
            else
                clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        return fieldList.toArray(fields);
    }

    /**
    * 功能描述:获取此类实现的所有接口(包含其父类实现的接口)
    * @param:[clazz]
    * @return:java.util.List<java.lang.Class<?>>
    * @Date: 2018/12/5
    */
    private static List<Class<?>> getAllInterfaces(Class<?> clazz){
        List<Class<?>> interfaces = new ArrayList<>();
        while (clazz != null){
            interfaces.addAll(Arrays.asList(clazz.getInterfaces()));
            clazz = clazz.getSuperclass();
        }
        return interfaces;
    }

    /** 获取bean 或者 Map 属性 */
    private static  <T> Object getResult(T bean, String field){
        Class<?> clazz = bean.getClass();
        if(bean instanceof Map){
            return ((Map) bean).get(field);
        }else {
            Object invoke;
            try {
                Method getMethod = getPropertyMethod(field, clazz, GET);
                invoke = getMethod.invoke(bean);
            } catch (IllegalAccessException e) {
                throw new com.jy.common.exception.reflect.InvocationTargetException();
            } catch (InvocationTargetException e) {
                throw new com.jy.common.exception.reflect.InvocationTargetException();
            }
            return invoke;
        }
    }

    /** 设置bean或者 Map 属性 */
    private static  <T> void setProperty(T bean, String field, Object value) {
        if(value == null){
            return;
        }
        Class<?> clazz = bean.getClass();
        if(bean instanceof Map){
            ((Map)bean).put(field,value);
        }else {
            try {
                Method setMethod = getPropertyMethod(field, clazz, SET);
                setMethod.invoke(bean,value);
            } catch (IllegalAccessException e) {
                throw new com.jy.common.exception.reflect.InvocationTargetException();
            } catch (InvocationTargetException e) {
                throw new com.jy.common.exception.reflect.InvocationTargetException();
            }
        }
    }

    /**
    * 功能描述:获取属性get/set方法(包含父类)
    * @param:[field, clazz, methodPrefix]
    * @return:java.lang.reflect.Method
    * @Date: 2018/12/12
    */
    private static Method getPropertyMethod(String field,Class clazz,MethodPrefix methodPrefix){
        Method method;
        String firChar = field.substring(0, 1);
        try {
            String prefix;
            List<Class<?>> paramClass = new ArrayList<>();
            Field declaredField = null;
            Class<?> tempClass = clazz;
            while (tempClass != null && declaredField == null){
                declaredField = getDeclaredField(tempClass,field);
                tempClass = tempClass.getSuperclass();
            }
            if(declaredField == null){
                throw new com.jy.common.exception.reflect.NoSuchFieldException();
            }
            switch (methodPrefix){
                case GET:prefix = getPrefix;break;
                case SET:prefix = setPrefix;paramClass.add(declaredField.getType());break;
                default :prefix = getPrefix;break;
            }
            Class[] fields = new Class[paramClass.size()];
            fields = paramClass.toArray(fields);
            method = clazz.getMethod(prefix + field.replaceFirst(firChar, firChar.toUpperCase()),fields);
        } catch (NoSuchMethodException e) {
            throw new com.jy.common.exception.reflect.NoSuchMethodException();
        }
        return method;
    }

    private static Field getDeclaredField(Class<?> clazz, String field){
        Field result = null;
        try {
            result = clazz.getDeclaredField(field);
        } catch (NoSuchFieldException e) {
            throw new com.jy.common.exception.reflect.NoSuchMethodException();
        }
        return result;
    }

}
