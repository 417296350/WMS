package com.company.wms.web.action;

import com.alibaba.fastjson.JSON;
import com.company.wms.annotation.Permission;
import com.company.wms.domain.SystemMenu;
import com.company.wms.query.impl.SystemMenuQuery;
import com.company.wms.web.service.ISystemMenuService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xd on 2018/5/5.
 */
public class SystemMenuAction extends BaseAction {


    // 给JSP返回数据的KEY
    private final String K_PARENT_MENU_NAME = "parentName";
    private final String K_MENU_STRUCTURES = "menuStructures";

    // ————————业务操作模块
    @Setter
    private ISystemMenuService systemMenuService;

    // ————————接受前端参数
    // 接收前端提交的查询条件
    @Getter
    private SystemMenuQuery query = new SystemMenuQuery();
    // 接受前端提交的系统菜单信息(保存|更新)
    @Getter
    private SystemMenu systemMenu = new SystemMenu();


    // 展示列表
    @Permission("系统菜单列表")
    @InputConfig(methodName = "input")
    public String execute(){
        try {
            ActionContextPut(K_PAGE_RESULT_KEY,systemMenuService.getQuery(query));
            ActionContextPut(K_MENU_STRUCTURES,systemMenuService.getMenuStructure(query.getParentId()));
        }catch (Exception e){
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return K_LIST_JSP;
    }

    // 保存更新操作
    public String getChildMenusByParentSn() {
        try {
            writeJSONTextToResponse(systemMenuService.getChildMenusJsonByParentSn(query.getParentSn()));
        }catch (Exception e){
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return NONE;
    }

    // 进入编辑界面
    @Permission("系统菜单编辑")
    public String input(){

        // 1.获取当菜单的上级菜单
        ActionContextPut(K_PARENT_MENU_NAME,systemMenuService.getMenuName(query.getParentId()));

        // 2.判断是保存还是更新
        if (systemMenu != null && systemMenu.getId() != null){
            // 更新
            systemMenu = systemMenuService.get(systemMenu);
        }

        return K_INPUT_JSP;
    }

    // 保存更新操作
    @Permission("系统菜单保存|修改")
    public String saveOrUpdate(){
        try {
            // 保存、更新操作
            if ( systemMenu.getId() == null ){
                // 保存操作
                systemMenuService.save(systemMenu);
                addActionMessage("保存成功");
            }else {
                // 更新操作
                systemMenuService.update(systemMenu);
                addActionMessage("更新成功");
            }

            // 如果是根目标，则设置systemMenu的父菜单Id为-1，目的拼接URL参数。
            if (systemMenu.getParentMenu() == null){
                SystemMenu parentMenu = new SystemMenu();
                parentMenu.setId(-1L);
                systemMenu.setParentMenu(parentMenu);
            }

        }catch (Exception e){
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }

    // 删除操作
    @Permission("系统菜单删除")
    public String delete() throws IOException {
        try {
            systemMenuService.delete(systemMenu);
            writePlainTextToResponse("删除成功");
        }catch (Exception e){
            writePlainTextToResponse("菜单正在被使用，不可删除!");
        }
        return NONE;
    }

    // 在saveOrUpdate操作前调用
    public void prepareSaveOrUpdate(){
        if (systemMenu != null && systemMenu.getId() != null){
            systemMenu = systemMenuService.get(systemMenu);
        }
    }


}
