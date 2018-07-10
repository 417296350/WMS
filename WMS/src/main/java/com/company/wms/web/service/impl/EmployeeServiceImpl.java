package com.company.wms.web.service.impl;

import com.company.wms.dao.IEmployeeDAO;
import com.company.wms.domain.Employee;
import com.company.wms.query.impl.BaseQuery;
import com.company.wms.result.PageResult;
import com.company.wms.web.service.IEmployeeService;
import lombok.Setter;

import java.util.List;

/**
 * Created by xd on 2018/5/5.
 */
public class EmployeeServiceImpl implements IEmployeeService{

    @Setter
    private IEmployeeDAO employeeDAO;

    @Override
    public PageResult getQuery(BaseQuery query) {
        return employeeDAO.getQuery(query);
    }

    @Override
    public Employee get(Employee employee) {
        return employeeDAO.get(employee.getId());
    }

    @Override
    public List<Employee> getAllList() {
        return employeeDAO.getAllList();
    }

    @Override
    public void save(Employee employee) {
        employeeDAO.save(employee);
    }

    @Override
    public void update(Employee employee) {
        employeeDAO.update(employee);
    }

    @Override
    public void delete(Employee employee) {
        employeeDAO.delete(employee);
    }

    @Override
    public void batchDelete(List<Long> ids) {
        employeeDAO.batchDelete(ids);
    }
}
