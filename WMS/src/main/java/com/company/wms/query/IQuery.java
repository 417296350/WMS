package com.company.wms.query;

/**
 * Created by xd on 2018/5/7.
 */
public interface IQuery {

    /**
     * 获取查询条件的HQL
     * @return 条件的HQL
     */
    public String getQueryHQL();

    /**
     * 获取查询条件HQL所对应的参数
     * @return 查询条件HQL所对应的参数
     */
    public Object[] getQueryParams();
}
