package ${basePkg}.web.service.impl;

import ${basePkg}.dao.I${className}DAO;
import ${basePkg}.domain.${className};
import ${basePkg}.query.impl.BaseQuery;
import ${basePkg}.result.PageResult;
import ${basePkg}.web.service.I${className}Service;
import lombok.Setter;

import java.util.List;

/**
 * Created by xd on 2018/5/5.
 */
public class ${className}ServiceImpl implements I${className}Service{

    @Setter
    private I${className}DAO ${objectName}DAO;

    @Override
    public List<${className}> getAllList() {
        return ${objectName}DAO.getAllList();
    }

    @Override
    public PageResult<${className}> getQuery(BaseQuery query) {
        return ${objectName}DAO.getQuery(query);
    }

    @Override
    public ${className} get(${className} ${objectName}) {
        return ${objectName}DAO.get(${objectName}.getId());
    }

    @Override
    public void save(${className} ${objectName}) {
        ${objectName}DAO.save(${objectName});
    }

    @Override
    public void update(${className} ${objectName}) {
        ${objectName}DAO.update(${objectName});
    }

    @Override
    public void delete(${className} ${objectName}) {
        ${objectName}DAO.delete(${objectName});
    }

    @Override
    public void batchDelete(List<Long> ids) {
        ${objectName}DAO.batchDelete(ids);
    }
}
