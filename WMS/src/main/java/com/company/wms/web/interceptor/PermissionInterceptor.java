package com.company.wms.web.interceptor;

import com.company.wms.annotation.Permission;
import com.company.wms.domain.Employee;
import com.company.wms.utils.UserContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by xd on 2018/5/10.
 */
public class PermissionInterceptor extends AbstractInterceptor{

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {

        // 1.获取当前登录用户信息
        Employee employee = UserContext.getUserFromSession();

        // 2.校验
        // 如果是超级管理员则直接访问所有方法
        if (  employee.getAdmin() != null && employee.getAdmin() == true){
            return actionInvocation.invoke();
        }

        // 判断当前访问的方法是否有permission标签，如果没有则直接访问
        String mtd = actionInvocation.getProxy().getMethod();
        Method method = actionInvocation.getProxy().getAction().getClass().getDeclaredMethod(mtd);
        Annotation annotation = method.getAnnotation(Permission.class);
        if ( annotation == null ){
            actionInvocation.invoke();
        }


        // 判断当前登录用户是否有权限执行当前方法，如果有则放行
        Set<String> permissionMethods =  UserContext.getUserPermissionsFromSession();
        for (String permissionMethod : permissionMethods) {
            if (permissionMethod.equals(method)){
                return actionInvocation.invoke();
            }
        }

        return "no_permission";
    }
}
