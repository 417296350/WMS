package com.company.generator;

import com.company.wms.domain.BaseDomain;
import com.company.wms.domain.Employee;
import com.company.wms.domain.SystemMenu;
import lombok.Getter;
import lombok.Setter;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xd on 2018/6/20.
 */
@Setter @Getter
public class TemplateInfo {

    // ---- 属性
    private String basePkg;         //占位符对应的基本包名
    private String className;       //占位符对应的对象类的名称
    private String objectName;      //占位符对应的对象名称
    private String cnName;          //占位符对应对象的中文名称
    private List<String> propertyNames = new ArrayList<>();   //占位符对应的对象名称

    public TemplateInfo(Class<?> clz,String cnName){
        this.basePkg = clz.getPackage().getName().substring(0,clz.getPackage().getName().lastIndexOf("."));
        this.className = clz.getSimpleName();
        this.objectName = (new StringBuilder(10).append(Character.toLowerCase(this.className.charAt(0))).append(this.className.substring(1)).toString());
        this.cnName = cnName;
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(clz,BaseDomain.class);
            propertyNames.clear();
            for (PropertyDescriptor p : beanInfo.getPropertyDescriptors()) {
                propertyNames.add(p.getName());
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }

    }

}
