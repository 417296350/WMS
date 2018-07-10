package com.company.wms.utils;

import lombok.Getter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xd on 2018/5/10.
 */
public class ReflectUtils {

    // 描述方法和方法所贴标签de mode
    public static class MethodAndAnnotationMode<T> {

        @Getter
        private Method method;
        @Getter
        private T annotation;

        public MethodAndAnnotationMode(Method method, T annotation) {
            this.method = method;
            this.annotation = annotation;
        }

    }

    /**
     * 获取给定objects集合中的所有object对象中被annotation标签注释的方法和方法所在的annotation注释
     * 同时把方法和所对应的annotation注释封装成MethodAndAnnotationMode对象返回。
     * @param objects 要被获取被annotation标签注释方法对象的数组
     * @param annotation 标签
     * @return  返回被objects集合中的所有object对象中被annotation的方法和annotation。
     */
    public static <T extends Annotation> List<MethodAndAnnotationMode> getMethodsHaveAnnotationFromObjects(Object[] objects, Class<T> annotation){

        List<MethodAndAnnotationMode> modes = new ArrayList<>();

        // 遍历所有Object对象
        for (Object object : objects) {

            Method[] tempMethods =  object.getClass().getMethods();

            // 获取所有方法
            for (Method tempMethod : tempMethods) {

                T tempAnnotation  = tempMethod.getAnnotation(annotation);

                if (tempAnnotation != null){

                    MethodAndAnnotationMode mode = new MethodAndAnnotationMode<T>(tempMethod,tempAnnotation);

                    modes.add(mode);
                }

            }

        }

        return modes;
    };

    /**
     * 通过传入的Method对象返回此方法对应的权限定名的方法名
     * @param method 方法
     * @return 方法的权限定名
     */
    public static String getPermissionsMethod(Method method){
        return method.getDeclaringClass().getName() + ":" +  method.getName();
    }

    /**
     * 通过传入的Class类和方法名返回此方法对应的权限定名的方法名
     * @param clz 类
     * @param method 方法名
     * @return 返回方法的权限定名
     */
    public static String getPermissionsMethod(Class clz,String method){
        return clz.getName() + ":" + method;
    }
}
