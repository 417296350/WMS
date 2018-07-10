package com.company.wms.web.service;

import com.company.wms.domain.Department;
import com.company.wms.query.impl.BaseQuery;
import com.company.wms.result.PageResult;

import java.util.List;

/**
 * Created by xd on 2018/5/5.
 */
public interface IDepartmentService {

    /**
     * 获取所有Department对象
     */
    public List<Department> getAllList();

    /**
     * 获取PageResult对象
     */
    public PageResult getQuery(BaseQuery query);

    /**
     * 获取Department对象
     */
    public Department get(Department department);

    /**
     * 保存Department对象
     */
    public void save(Department department);

    /**
     * 更新Department对象
     */
    public void update(Department department);

    /**
     * 更新Department对象
     */
    public void delete(Department department);

    /**
     * 批量删除Department对象
     * @param ids
     */
    public void batchDelete(List<Long> ids);
}
