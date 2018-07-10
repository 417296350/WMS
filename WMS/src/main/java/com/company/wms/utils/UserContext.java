package com.company.wms.utils;

import com.company.wms.domain.Employee;
import com.opensymphony.xwork2.ActionContext;

import java.util.Set;

/**
 * Created by xd on 2018/5/11.
 */
public class UserContext {

    // 存在session中的用户信息所对应的key
    public static final String K_USER_IN_SESSION = "user_in_session";
    // 存在session中的用户信息所拥有的权限Key
    public static final String K_USER_PERMISSION_IN_SESSION = "user_in_permission_session";

    /**
     * 保存或删除用户信息到session中
     * @param user user为null则删除，user不为null则保存
     */
    public static void setSessionFromUser(Employee user){

        if ( user != null ){

            // 添加
            ActionContext.getContext().getSession().put(K_USER_IN_SESSION,user);
        }else {

            // 删除
            ActionContext.getContext().getSession().remove(K_USER_IN_SESSION);
        }

    }

    public static Employee getUserFromSession(){
        return (Employee) ActionContext.getContext().getSession().get(K_USER_IN_SESSION);
    }



    /**
     * 保存或删除用户拥有的权限到session中
     * @param permissions permissions为null则删除，permissions不为null则保存
     */
    public static void setSessionFromUserPermissions(Set<String> permissions){

        if ( permissions != null ){
            // 添加
            ActionContext.getContext().getSession().put(K_USER_PERMISSION_IN_SESSION,permissions);
        }else {
            // 删除
            ActionContext.getContext().getSession().remove(K_USER_PERMISSION_IN_SESSION);
        }
    }

    public static Set<String> getUserPermissionsFromSession(){
        return (Set<String>) ActionContext.getContext().getSession().get(K_USER_PERMISSION_IN_SESSION);
    }

}
