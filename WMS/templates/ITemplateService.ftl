package ${basePkg}.web.service;

import ${basePkg}.domain.${className};
import ${basePkg}.query.impl.BaseQuery;
import ${basePkg}.result.PageResult;

import java.util.List;

/**
 * Created by xd on 2018/5/5.
 */
public interface I${className}Service {

    /**
     * 获取所有${cnName}对象
     * @return 返回List<${className}>
     */
    public List<${className}> getAllList();

    /**
     * 根据查询条件进行查询，返回PageResult对象
     * @param query 查询条件对象
     * @return PageResult对象(封装了${cnName}对象集合、分页信息等)
     */
    public PageResult<${className}> getQuery(BaseQuery query);

    /**
     * 获取所有${cnName}
     * @return 返回List<${className}>
     */
    public ${className} get(${className} ${objectName});

    /**
     * 保存${cnName}对象
     * @param ${objectName} 要被保存的${className}对象
     */
    public void save(${className} ${objectName});

    /**
     * 更新${cnName}对象
     * @param ${objectName} 要被更新的${className}对象
     */
    public void update(${className} ${objectName});

    /**
     * 删除${cnName}对象
     * @param ${objectName} 要被删除的${className}对象
     */
    public void delete(${className} ${objectName});

    /**
     * 批量删除${cnName}对象
     * @param ids
     */
    public void batchDelete(List<Long> ids);

}
