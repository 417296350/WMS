package com.company.wms.dao;

import com.company.wms.domain.SystemMenu;

import java.util.List;


public interface ISystemMenuDAO extends IBaseDAO<SystemMenu>{

    /**
     * 获取所有子菜单
     * @return 返回子菜单集合
     */
    List<SystemMenu> getAllChildMenus();

    /**
     * 根据父菜单sn获取所有子菜单
     * @param parentSn 父菜单sn
     * @return 子菜单集合
     */
    List<SystemMenu> getChildMenusByParentSn(String parentSn);
}
