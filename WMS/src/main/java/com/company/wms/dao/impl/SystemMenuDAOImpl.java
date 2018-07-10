package com.company.wms.dao.impl;

import com.company.wms.dao.ISystemMenuDAO;
import com.company.wms.domain.SystemMenu;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by xd on 2018/5/4.
 */
public class SystemMenuDAOImpl extends BaseDAOImpl<SystemMenu> implements ISystemMenuDAO{

    @Override
    public List<SystemMenu> getAllChildMenus() {

        String hql = "SELECT obj FROM SystemMenu obj WHERE obj.parentMenu IS NOT NULL";

       return getHibernateTemplate().execute(session -> {

            // 获取满足查询条件的数据总数
            Query<SystemMenu> qry = session.createQuery(hql,SystemMenu.class);


            return qry.list();
        });

    }

    @Override
    public List<SystemMenu> getChildMenusByParentSn(String parentSn) {
        String hql = "SELECT obj FROM SystemMenu obj WHERE obj.parentMenu.sn = :parentMenuSn";

        return getHibernateTemplate().execute(session -> {

            // 获取满足查询条件的数据总数
            Query<SystemMenu> qry = session.createQuery(hql,SystemMenu.class);

            // 设置参数
            qry.setParameter("parentMenuSn",parentSn);

            return qry.list();
        });

    }
}
