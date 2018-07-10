package com.company.wms.dialect;

import org.hibernate.dialect.MySQL5InnoDBDialect;

/**
 * Created by xd on 2018/5/6.
 */
public class MyDialect extends MySQL5InnoDBDialect {
    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
