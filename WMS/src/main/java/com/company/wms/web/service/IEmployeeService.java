package com.company.wms.web.service;

import com.company.wms.domain.Employee;
import com.company.wms.query.impl.BaseQuery;
import com.company.wms.result.PageResult;

import java.util.List;

/**
 * Created by xd on 2018/5/5.
 */
public interface IEmployeeService {

    /**
     * 获取PageResult对象
     */
    public PageResult getQuery(BaseQuery query);

    /**
     * 获取PageResult对象
     */
    public Employee get(Employee employee);

    /**
     * 获取所有Employee对象
     */
    public List<Employee> getAllList();

    /**
     * 保存Employee对象
     */
    public void save(Employee employee);

    /**
     * 更新Employee对象
     */
    public void update(Employee employee);

    /**
     * 删除Employee对象
     */
    public void delete(Employee employee);

    /**
     * 批量删除Employee对象
     * @param ids
     */
    public void batchDelete(List<Long> ids) ;
}
