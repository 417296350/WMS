package com.company.wms.web.service.impl;

import com.company.wms.dao.IRoleDAO;
import com.company.wms.domain.Role;
import com.company.wms.query.impl.BaseQuery;
import com.company.wms.result.PageResult;
import com.company.wms.web.service.IRoleService;
import lombok.Setter;

import java.util.List;

/**
 * Created by xd on 2018/5/5.
 */
public class RoleServiceImpl implements IRoleService{

    @Setter
    private IRoleDAO roleDAO;

    @Override
    public List<Role> getAllList() {
        return roleDAO.getAllList();
    }

    @Override
    public PageResult<Role> getQuery(BaseQuery query) {
        return roleDAO.getQuery(query);
    }

    @Override
    public Role get(Role role) {
        return roleDAO.get(role.getId());
    }

    @Override
    public void save(Role role) {
        roleDAO.save(role);
    }

    @Override
    public void update(Role role) {
        roleDAO.update(role);
    }

    @Override
    public void delete(Role role) {
        roleDAO.delete(role);
    }

    @Override
    public void batchDelete(List<Long> ids) {
        roleDAO.batchDelete(ids);
    }
}
