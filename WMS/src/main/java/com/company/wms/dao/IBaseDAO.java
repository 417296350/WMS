package com.company.wms.dao;

import com.company.wms.query.impl.BaseQuery;
import com.company.wms.result.PageResult;

import java.util.List;

/**
 * Created by xd on 2018/5/5.
 */
public interface IBaseDAO<T> {

    /**
     * 根据id来获取数据库中对应的数据
     * @param id 要被查询的id
     * @return 返回对象
     */
    public T get(Long id);

    /**
     * 把传入的对象保存到数据库中
     * @param t 要被保存的对象
     */
    public void save(T t);

    /**
     * 更新传入的对象到数据库
     * @param t
     */
    public void update(T t);

    /**
     * 根据id删除数据库中对应的数据
     * @param t 要被删除对象的t
     */
    public void delete(T t);

    /**
     * 获取数据库中T类型对应的所有数据
     * @return
     */
    public List<T> getAllList();

    /**
     * 根据查询条件和分页要求进行查询，把查询对象封装到PageResult
     * @param query 查询对象(封装了查询条件、查询参数、分页信息)
     * @return 把数据库返回数据封装到PageResult对象进行返回。
     */
    public PageResult<T> getQuery(BaseQuery query);

    /**
     * 根据查询条件进行查询，返回满足查询条件结果集元素的数量。
     * @param query 查询条件对象
     * @return 返回结果集元素数量。
     */
    public Long getCountFromQuery(BaseQuery query);

    /**
     * 批量删除
     * @param ids 批量删除的id数组
     */
    void batchDelete(List<Long> ids);
}
