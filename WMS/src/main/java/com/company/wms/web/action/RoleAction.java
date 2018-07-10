package com.company.wms.web.action;

import com.company.wms.annotation.Permission;
import com.company.wms.domain.Role;
import com.company.wms.query.impl.RoleQuery;
import com.company.wms.web.service.IPermissionService;
import com.company.wms.web.service.IRoleService;
import com.company.wms.web.service.ISystemMenuService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;

/**
 * Created by xd on 2018/5/5.
 */
public class RoleAction extends BaseAction {

    private final String K_PERMISSION_KEY = "permissions";
    private final String K_MENU_KEY = "systemMenus";

    // ————————业务操作模块
    @Setter
    private IRoleService roleService;
    @Setter
    private IPermissionService permissionService;
    @Setter
    private ISystemMenuService systemMenuService;

    // ————————接受前端参数
    // 接收前端提交的查询条件
    @Getter
    private RoleQuery query = new RoleQuery();
    // 接受前端提交的角色信息(保存|更新)
    @Getter
    private Role role = new Role();


    // 展示列表
    @Permission("角色列表")
    @InputConfig(methodName = "input")
    public String execute(){
        try {
            ActionContextPut(K_PAGE_RESULT_KEY,roleService.getQuery(query));
        }catch (Exception e){
            e.printStackTrace();
            addActionError(e.getMessage());
        }

        return K_LIST_JSP;
    }

    // 进入编辑界面
    @Permission("角色编辑")
    public String input(){

        // 1.获取所有权限
        ActionContextPut(K_PERMISSION_KEY,permissionService.getAllList());
        ActionContextPut(K_MENU_KEY,systemMenuService.getAllChildMenus());

        // 2.判断是保存还是更新
        if (role != null && role.getId() != null){
            // 更新
            role = roleService.get(role);
        }else {
            // 保存（为了防止input界面的select报错，select不准许list对应的集合为null）
            role.setPermissions(new HashSet<>());
        }

        return K_INPUT_JSP;
    }

    // 保存更新操作
    @Permission("角色保存|修改")
    public String saveOrUpdate(){
        try {
            if ( role.getId() == null ){
                // 保存操作
                roleService.save(role);
                addActionMessage("保存成功");
            }else {
                // 更新操作
                roleService.update(role);
                addActionMessage("更新成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }

    // 删除操作
    @Permission("角色删除")
    public String delete(){
        try {
            roleService.delete(role);
            writePlainTextToResponse("删除成功");
        }catch (Exception e){
            writePlainTextToResponse("菜单正在被使用，不可删除!");
        }
        return NONE;
    }

    // 批量删除操作
    @Permission("批量角色删除")
    public String batchDelete(){
        roleService.batchDelete(this.getIds());
        addActionMessage("删除成功");
        return NONE;
    }

    // 在saveOrUpdate操作前调用
    public void prepareSaveOrUpdate(){
        if (role != null && role.getId() != null){
            role = roleService.get(role);
        }
        role.getPermissions().clear();
        role.getSystemMenus().clear();
    }
}
