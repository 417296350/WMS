package com.company.wms.web.service;

import com.company.wms.domain.Role;
import com.company.wms.query.impl.BaseQuery;
import com.company.wms.result.PageResult;

import java.util.List;

/**
 * Created by xd on 2018/5/5.
 */
public interface IRoleService {

    /**
     * 获取所有角色
     * @return 返回List<角色>
     */
    public List<Role> getAllList();

    /**
     * 根据查询条件进行查询，返回PageResult对象
     * @param query 查询条件对象
     * @return PageResult对象(封装了Role集合、分页信息等)
     */
    public PageResult<Role> getQuery(BaseQuery query);

    /**
     * 获取所有角色
     * @return 返回List<角色>
     */
    public Role get(Role role);

    /**
     * 保存角色对象
     * @param role 要被保存的角色对象
     */
    public void save(Role role);

    /**
     * 更新角色对象
     * @param role 要被更新的角色对象
     */
    public void update(Role role);

    /**
     * 删除角色对象
     * @param role 要被删除的角色对象
     */
    public void delete(Role role);

    /**
     * 批量删除Role对象
     * @param ids
     */
    public void batchDelete(List<Long> ids);

}
