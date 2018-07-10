package com.company.wms.query.impl;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by xd on 2018/5/7.
 */
@Setter @Getter
public class EmployeeQuery extends BaseQuery {

    // 要被查询的姓名和邮箱
    private String keyword;
    // 要被查询的部门ID
    private Long departId;


    // 子类自定义查询信息
    @Override
    public void customQueryQhlAndQueryParamts() {

        if (!StringUtils.isBlank(keyword)){
            super.addQueryHqlAndParamts("obj.name LIKE ? OR obj.email LIKE ?","%" + keyword + "%","%" + keyword + "%");
        }

        if (departId != null && departId!=-1){
            super.addQueryHqlAndParamt("obj.depart.id = ?",departId);
        }
    }

}
