package com.company.wms.query.impl;

import com.company.wms.query.IQuery;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xd on 2018/5/7.
 */
public abstract class BaseQuery implements IQuery{


    @Setter @Getter
    // 分页中一分页准许的最大数量(默认为5)
    private Integer pageSize = 5;
    @Setter @Getter
    // 分页中要显示的页数(默认为1)
    private Integer currentPage = 1;

    // 存储查询HQL和查询参数的List
    private List<String > queryHQLs = new ArrayList<>();
    private List<Object> queryParamts = new ArrayList<>();

    // 抽象方法：子类必须实现，子类自定义去添加查询条件和查询参数
    public abstract void customQueryQhlAndQueryParamts();

    /**
     * 添加查询信息的Hql和参数
     * @param queryHql 查询信息的HQL
     * @param queryParamt 查询信息HQL对应的参数
     */
    protected void addQueryHqlAndParamt(String queryHql,Object queryParamt){
        addQueryHqlAndParamts(queryHql,queryParamt);
    }

    /**
     * 添加查询信息的Hql和参数
     * @param queryHql 查询信息的HQL
     * @param queryParamts 查询信息HQL对应的参数
     */
    protected void addQueryHqlAndParamts(String queryHql,Object... queryParamts){
        queryHQLs.add("(" + queryHql + ")");

        for(int i = 0;i < queryParamts.length;i++){
            this.queryParamts.add(queryParamts[i]);
        }

    }

    @Override
    public String getQueryHQL() {

        // 1.清空查询条件和查询参数(防止JSP不创建查询对象，而是直接使用上一次的查询对象，此时会导致查询信息重复)
        clearQueryQHLsAndQueryParamts();

        // 2.添加查询信息
        customQueryQhlAndQueryParamts();

        StringBuilder quHQL = new StringBuilder();

        // 拼接查询条件的HQL
        int i = 0;
        for (Object queryHQL : queryHQLs) {

            if ( i == 0 ){
                quHQL.append(" WHERE ");
            }

            quHQL.append(queryHQL);

            if (i < (queryHQLs.size() - 1) ){
                quHQL.append(" AND ");
            }

            i++;
        }

        return quHQL.toString();
    }

    @Override
    public Object[] getQueryParams() {
        return queryParamts.toArray();
    }

    // 清空存储查询HQL和查询参数的List
    private void clearQueryQHLsAndQueryParamts(){
        queryHQLs.clear();
        queryParamts.clear();
    }

}
