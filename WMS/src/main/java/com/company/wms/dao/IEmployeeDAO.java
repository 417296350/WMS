package com.company.wms.dao;

import com.company.wms.domain.Employee;


/**
 * Created by xd on 2018/5/4.
 */
public interface IEmployeeDAO extends IBaseDAO<Employee>{

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 返回登录用户信息
     */
    public Employee checkLogin(String username, String password);

}
