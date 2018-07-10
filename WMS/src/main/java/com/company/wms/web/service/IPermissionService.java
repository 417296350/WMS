package com.company.wms.web.service;

import com.company.wms.domain.Permission;
import com.company.wms.domain.SystemMenu;
import com.company.wms.query.impl.BaseQuery;
import com.company.wms.result.PageResult;

import java.util.List;

/**
 * Created by xd on 2018/5/5.
 */
public interface IPermissionService {

    /**
     * 获取所有权限
     * @return 返回List<权限>
     */
    public List<Permission> getAllList();

    /**
     * 根据查询条件进行查询，返回PageResult对象
     * @param query 查询条件对象
     * @return PageResult对象(封装了Permission集合、分页信息等)
     */
    public PageResult<Permission> getQuery(BaseQuery query);

    /**
     * 保存权限对象
     * @param permission 要被保存的权限对象
     */
    public void save(Permission permission);

    /**
     * 更新权限对象
     * @param permission 要被更新的权限对象
     */
    public void update(Permission permission);

    /**
     * 加载权限,把程序中的方法上有permission标签的方法(权限方法)所对应的表达式加载到数据库中
     */
    public void reloadPermission();

}
