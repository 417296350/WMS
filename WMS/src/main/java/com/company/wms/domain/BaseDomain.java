package com.company.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

/**
 * Created by xd on 2018/5/5.
 */

public class BaseDomain {

    // id
    @Setter @Getter
    protected Long id;

    /**
     * 子类对象可调用，调用参数objects集合中对象的getName方法，把方法返回的值拼接为[obj.getName,obj.getName,...]字符串返回
     * @param objects 装有obj对象集合
     * @return [obj.getName,obj.getName,...]
     * @throws NoSuchMethodException 异常
     * @throws InvocationTargetException 异常
     * @throws IllegalAccessException 异常
     */
    protected String getNameStrFromObjects(Set<?> objects) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (objects ==null || objects.size() == 0){
            return "";
        }
        StringBuilder str = new StringBuilder(10);
        str.append("[");
        for (Object obj : objects) {
            str.append(obj.getClass().getMethod("getName",null).invoke(obj) + ",");
        }
        str.deleteCharAt(str.length()-1);
        str.append("]");
        return String.valueOf(str);
    }

}
