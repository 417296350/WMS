package com.company.wms.web.action;

import com.company.wms.annotation.Permission;
import com.company.wms.domain.Department;
import com.company.wms.query.IQuery;
import com.company.wms.query.impl.BaseQuery;
import com.company.wms.query.impl.ObjectQuery;
import com.company.wms.web.service.IDepartmentService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xd on 2018/5/5.
 */
public class DepartmentAction extends BaseAction {

    // ————————业务操作模块
    @Setter
    private IDepartmentService departmentService;

    // ————————接受前端参数
    // 查询参数(查询条件)
    @Getter
    private BaseQuery query = new ObjectQuery();
    // 部门参数(添加|修改传递的部门)
    @Getter
    private Department department = new Department();


    // 访问默认当前Action会调用-进入list.jsp显示界面
    @Permission("部门列表")
    @InputConfig(methodName = "input")
    public String execute(){
        try {
            ActionContextPut(K_PAGE_RESULT_KEY,departmentService.getQuery(query));
        }catch (Exception e){
             e.printStackTrace();
             addActionError(e.getMessage());
       }
        return K_LIST_JSP;
    }

    // 新增|查询时会调用-进入input.jsp编辑界面
    @Permission("部门编辑")
    public String input(){

        if (department != null && department.getId() != null){
            // 编辑
            department = departmentService.get(department);
        }

        return K_INPUT_JSP;
    }

    // 新增|修改的逻辑操作
    @Permission("部门保存|修改")
    public String saveOrUpdate(){

        try {
            if ( department.getId() == null ){
                // 保存操作
                departmentService.save(department);
                addActionMessage(" 保存成功");
            }else {
                // 更新操作
                departmentService.update(department);
                addActionMessage(" 更新成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            addActionError(e.getMessage());
        }

        return SUCCESS;
    }

    // 删除操作
    @Permission("部门删除")
    public String delete(){
        departmentService.delete(department);
        return NONE;
    }

    // 批量删除操作
    @Permission("批量部门删除")
    public String batchDelete(){
        departmentService.batchDelete(this.getIds());
        addActionMessage("删除成功");
        return NONE;
    }

    // 在saveOrUpdate操作前调用
    public void prepareSaveOrUpdate(){
        if (department != null && department.getId() != null){
            department = departmentService.get(department);
        }
    }

}
