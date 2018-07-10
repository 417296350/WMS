package com.company.wms.query.impl;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by xd on 2018/5/7.
 */
@Setter @Getter
public class RoleQuery extends BaseQuery {

    // 要被查询的姓名
    private String name;


    // 子类自定义查询信息
    @Override
    public void customQueryQhlAndQueryParamts() {

        if (!StringUtils.isBlank(name)){
            super.addQueryHqlAndParamt("obj.name LIKE ?","%" + name + "%");
        }
    }

}
