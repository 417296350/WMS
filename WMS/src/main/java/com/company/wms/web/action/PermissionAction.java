package com.company.wms.web.action;

import com.company.wms.query.impl.RoleQuery;
import com.company.wms.web.service.IPermissionService;
import com.opensymphony.xwork2.ActionContext;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by xd on 2018/5/5.
 */
public class PermissionAction extends BaseAction {

    private final String K_PERMISSION_KEY = "permission";

    // ————————业务操作模块
    @Setter
    private IPermissionService permissionService;

    // ————————接受前端参数
    // 接收前端提交的查询条件
    @Getter
    private RoleQuery query = new RoleQuery();
    // 接受前端提交的角色信息(保存|更新)

    // 显示界面
    public String execute(){
        ActionContextPut(K_PAGE_RESULT_KEY,permissionService.getQuery(query));
        return K_LIST_JSP;
    }

    // 加载权限
    public String reloadPermission(){
        permissionService.reloadPermission();
        return SUCCESS;
    }

}
