package com.company.wms.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by xd on 2018/5/4.
 */
@Setter @Getter
public class Department extends BaseDomain{

    // 1.基本属性
    private String name;    //部门名称
    private String sn;      //部门编号

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", sn='" + sn + '\'' +
                '}';
    }
}
