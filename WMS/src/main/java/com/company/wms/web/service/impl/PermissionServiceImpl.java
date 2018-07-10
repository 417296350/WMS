package com.company.wms.web.service.impl;

import com.company.wms.dao.IPermissionDAO;
import com.company.wms.domain.Permission;
import com.company.wms.domain.SystemMenu;
import com.company.wms.query.impl.BaseQuery;
import com.company.wms.result.PageResult;
import com.company.wms.utils.ReflectUtils;
import com.company.wms.web.action.BaseAction;
import com.company.wms.web.service.IPermissionService;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by xd on 2018/5/5.
 */
public class PermissionServiceImpl implements IPermissionService,ApplicationContextAware{

    @Setter
    private IPermissionDAO permissionDAO;

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    @Override
    public List<Permission> getAllList() {
        return permissionDAO.getAllList();
    }

    @Override
    public PageResult<Permission> getQuery(BaseQuery query) {
        return permissionDAO.getQuery(query);
    }

    @Override
    public void save(Permission permission) {
        permissionDAO.save(permission);
    }

    @Override
    public void update(com.company.wms.domain.Permission permission) {
        permissionDAO.update(permission);
    }

    @Override
    public void reloadPermission() {

        // 0.获取数据库中所有Permission数据
        List<Permission> ps = permissionDAO.getAllList();
        Set<String> permissions = new HashSet<>();
        for (Permission p : ps) {
            permissions.add(p.getExpression());
        }

        // 1.扫描得到继承BaseAction的所有Action类
        Map<String,BaseAction> beanMap = applicationContext.getBeansOfType(BaseAction.class);
        Collection<BaseAction> actions =  beanMap.values();

        // 2.获取actions集合中所有action对象中所有包含permission标签的方法
        List<ReflectUtils.MethodAndAnnotationMode> modes = ReflectUtils.getMethodsHaveAnnotationFromObjects(actions.toArray(), com.company.wms.annotation.Permission.class);

        // 3.对比methods方法是否已在Permission数据库中存在
        for (ReflectUtils.MethodAndAnnotationMode mode : modes) {

            Method method = mode.getMethod();
            com.company.wms.annotation.Permission permission = (com.company.wms.annotation.Permission) mode.getAnnotation();

            String permissionName = ReflectUtils.getPermissionsMethod(method);

            if ( permissions == null || !permissions.contains(permissionName) ){

                Permission tempPermission = new Permission();
                tempPermission.setExpression(permissionName);
                tempPermission.setName(permission.value());
                permissionDAO.save(tempPermission);

            }
        }
    }

}
