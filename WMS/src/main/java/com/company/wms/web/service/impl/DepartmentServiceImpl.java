package com.company.wms.web.service.impl;

import com.company.wms.dao.IDepartmentDAO;
import com.company.wms.domain.Department;
import com.company.wms.query.impl.BaseQuery;
import com.company.wms.result.PageResult;
import com.company.wms.web.service.IDepartmentService;
import lombok.Setter;

import java.util.List;

/**
 * Created by xd on 2018/5/5.
 */
public class DepartmentServiceImpl implements IDepartmentService{

    // 数据库操作的DAO
    @Setter
    private IDepartmentDAO departmentDAO;


    @Override
    public List<Department> getAllList() {
        return departmentDAO.getAllList();
    }

    @Override
    public Department get(Department department) {
        return departmentDAO.get(department.getId());
    }

    @Override
    public void save(Department department) {
        departmentDAO.save(department);
    }

    @Override
    public void update(Department department){
        departmentDAO.update(department);
    }

    @Override
    public void delete(Department department) {
        departmentDAO.delete(department);
    }

    @Override
    public void batchDelete(List<Long> ids) {
        departmentDAO.batchDelete(ids);
    }

    @Override
    public PageResult getQuery(BaseQuery query){
        return departmentDAO.getQuery(query);
    }
}
