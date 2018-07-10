package com.company.wms.query.impl;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by xd on 2018/5/7.
 */
@Setter @Getter
public class SystemMenuQuery extends BaseQuery {

    // 当前菜单的父级菜单Id
    private Long parentId = -1L;
    // 当前菜单的父级菜单Sn
    private String parentSn;

    // 子类自定义查询信息
    @Override
    public void customQueryQhlAndQueryParamts() {

        if (parentId == -1){//根菜单
            addQueryHqlAndParamts("obj.parentMenu IS NULL");
        }else {//相应的子菜单
            addQueryHqlAndParamts("obj.parentMenu.id = ?",parentId);
        }

    }

}
