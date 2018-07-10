package com.company.wms.dao.impl;

import com.company.wms.dao.IEmployeeDAO;
import com.company.wms.domain.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xd on 2018/5/4.
 */
public class EmployeeDAOImpl extends BaseDAOImpl<Employee> implements IEmployeeDAO{

    @Override
    public Employee checkLogin(String username,String password){
        String hql = "SELECT e FROM Employee e WHERE e.name = ? AND e.password = ?";
        List<Employee> employees = (List<Employee>) getHibernateTemplate().find(hql,username,password);
        return employees.isEmpty() ? null : employees.get(0);
    }

}
