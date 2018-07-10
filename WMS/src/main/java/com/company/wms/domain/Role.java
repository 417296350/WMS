package com.company.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by xd on 2018/5/4.
 */
@Setter @Getter
public class Role extends BaseDomain {

    // 1.基本属性
    private String name;    //角色名称
    private String sn;      //角色编号

    // 2.关联属性
    // 多对多
    private Set<Permission> permissions = new HashSet<>();    //当前角色所拥有的权限(单向)
    private Set<SystemMenu> systemMenus = new HashSet<>();    //当前角色所拥有的菜单(单向)


    // 3.属性方法
    public String getAllPermissionName(){
        try {
            return super.getNameStrFromObjects(permissions);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(getId(), role.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
