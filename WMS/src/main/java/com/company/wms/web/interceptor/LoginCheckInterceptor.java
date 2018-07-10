package com.company.wms.web.interceptor;

import com.company.wms.domain.Employee;
import com.company.wms.utils.UserContext;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * Created by xd on 2018/5/10.
 */
public class LoginCheckInterceptor extends AbstractInterceptor{

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {

        // 1.获取当前登录用户信息
        Employee employee = UserContext.getUserFromSession();

        // 2.校验
        // 如果当前登录用户不存在,返回登录界面
        if ( employee == null || !(employee instanceof Employee)){
            return Action.LOGIN;
        }

        return actionInvocation.invoke();
    }
}
