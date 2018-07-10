package com.company.wms.web.service.impl;

import com.company.wms.dao.IEmployeeDAO;
import com.company.wms.domain.Employee;
import com.company.wms.domain.Permission;
import com.company.wms.domain.Role;
import com.company.wms.exception.NullParameterException;
import com.company.wms.utils.UserContext;
import com.company.wms.web.service.ILoginService;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by xd on 2018/5/4.
 */
public class LoginServiceImpl implements ILoginService{

    @Setter @Getter
    private IEmployeeDAO employeeDAO;

    @Override
    public void Login(String username,String password) {

        // 1.基础校验
        if (StringUtils.isEmpty(username)){
            // 判断账号是否为空
            throw new NullParameterException("请输入账号");
        }

        if (StringUtils.isEmpty(password)){
            // 判断密码是否为空
            throw new NullParameterException("请输入密码");
        }

        // 2.数据库校验
        Employee user = employeeDAO.checkLogin(username,password);
        if ( user == null ){
            // 如果传递的用户名和密码为空
            throw new NullParameterException("账号和密码不匹配");
        }

        // 3.把账号存储到session中
        Set<String> permissions = new HashSet<>();
        for (Role role : user.getRoles()) {

            for (Permission permission : role.getPermissions()) {
                permissions.add(permission.getExpression());
            }
        }

        // 4.把当前用户信息和权限信息存储到Session中
        UserContext.setSessionFromUser(user);
        UserContext.setSessionFromUserPermissions(permissions);
    }
}
