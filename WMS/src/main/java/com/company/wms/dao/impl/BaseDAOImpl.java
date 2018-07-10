package com.company.wms.dao.impl;

import com.company.wms.dao.IBaseDAO;
import com.company.wms.query.impl.BaseQuery;
import com.company.wms.result.PageResult;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.lang.reflect.ParameterizedType;
import java.util.List;


/**
 * Created by xd on 2018/5/5.
 */
public class BaseDAOImpl<T> extends HibernateDaoSupport implements IBaseDAO<T> {

    private Class<T> targetClass;

    public BaseDAOImpl(){
        // 子类创建对象时，一定会调用到父类的这个构造方法。
        // 此时在这里可以用通过反射机制来拿到子类(当前对象)的真实类型的父类上的泛型
        // public class EmployeeDAOImpl extends BaseDAOImpl<Employee>
        //      EmployeeDAOImpl是子类(当前真实对象)---BaseDAOImpl父类(当前构造方法)----所求：Employee需要的那个类型
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        targetClass = (Class<T>) type.getActualTypeArguments()[0];
    }

    @Override
    public T get(Long id) {
        return getHibernateTemplate().get(targetClass,id);
    }

    @Override
    public void save(T t) {
        getHibernateTemplate().save(t);
    }

    @Override
    public void update(T t) {
        getHibernateTemplate().update(t);
    }

    @Override
    public void delete(T t) {
        getHibernateTemplate().delete(t);
    }

    @Override
    public List<T> getAllList() {
        try {
            return getHibernateTemplate().findByExample(targetClass.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public PageResult<T> getQuery(BaseQuery query) {

        // 1.获取满足查询条件的数据总数
        Long count = this.getCountFromQuery(query);

        // 2.如果查询总数为0，则返回空Result
        if ( count == 0 ){
            return PageResult.empty();
        }

        // 3.如果查询总数不为0，则开始查询
        StringBuilder hql = new StringBuilder(100);
        hql.append("SELECT obj FROM ").append(targetClass.getSimpleName()).append(" obj ");

        List<T> objs = getHibernateTemplate().execute(session -> {

            // 设置查询条件
            Query<T> qry = session.createQuery(hql + query.getQueryHQL(),targetClass);

            // 设置查询参数
            setParamters(qry,query);

            // 设置分页
            qry.setFirstResult((query.getCurrentPage() - 1)*query.getPageSize()).setMaxResults(query.getPageSize());

            // 返回查询结果
            return qry.list();
        });

        PageResult<T> pageResult = new PageResult(count,query.getPageSize(),query.getCurrentPage(),objs);

        return pageResult;
    }

    @Override
    public void batchDelete(List<Long> ids) {

        String hql = "DELETE FROM " +  targetClass.getSimpleName() + " obj WHERE obj.id IN (:ids)";

        getHibernateTemplate().execute(session -> {

            Query query = session.createQuery(hql);

            query.setParameterList("ids",ids);

            query.executeUpdate();

            return null;
        });
    }

    @Override
    public Long getCountFromQuery(BaseQuery query) {

        StringBuilder hql = new StringBuilder(100);
        hql.append("SELECT COUNT(obj) FROM ").append(targetClass.getSimpleName()).append(" obj ");

        Long count = getHibernateTemplate().execute(session -> {

            // 获取满足查询条件的数据总数
            Query qry = session.createQuery(hql + query.getQueryHQL());

            // 设置查询参数
            setParamters(qry,query);

            return (Long) qry.uniqueResult();
        });

        return count;
    }


    private void setParamters(Query qry,BaseQuery query){
        for (int index = 0; index < query.getQueryParams().length; index++) {
            qry.setParameter(index,query.getQueryParams()[index]);
        }
    }


}
