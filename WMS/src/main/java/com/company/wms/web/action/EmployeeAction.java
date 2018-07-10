package com.company.wms.web.action;

import com.company.wms.annotation.Permission;
import com.company.wms.domain.Employee;
import com.company.wms.query.impl.EmployeeQuery;
import com.company.wms.web.service.IDepartmentService;
import com.company.wms.web.service.IEmployeeService;
import com.company.wms.web.service.IRoleService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by xd on 2018/5/5.
 */
public class EmployeeAction extends BaseAction{

    // 给JSP返回数据的KEY
    private final String K_DEPARTMENT_KEY = "departments";
    private final String K_ROLE_KEY = "roles";

    // ————————业务操作模块
    @Setter
    private IEmployeeService employeeService;
    @Setter
    private IDepartmentService departmentService;
    @Setter
    private IRoleService roleService;

    // ————————接受前端参数
    @Getter
    // 查询参数(查询条件)
    private EmployeeQuery query = new EmployeeQuery();
    @Getter
    // 员工参数(保存|修改|删除|的employee)
    private Employee employee = new Employee();



    // 访问默认当前Action会调用-进入list.jsp显示界面
    @Permission("员工列表")
    @InputConfig(methodName = "input")
    public String execute(){

        try {

            // 获取Department中的数据(部门列表显示)
            ActionContextPut(K_DEPARTMENT_KEY,departmentService.getAllList());

            // 获取数据库查询后的employees列表(员工列表显示)
            ActionContextPut(K_PAGE_RESULT_KEY,employeeService.getQuery(query));

        }catch (Exception e){
            e.printStackTrace();
            addActionError(e.getMessage());
        }


        return K_LIST_JSP;
    }

    // 新增|查询时会调用-进入input.jsp编辑界面
    public String input(){

        // 1.获取Department中的数据(部门列表显示)
        ActionContextPut(K_DEPARTMENT_KEY,departmentService.getAllList());

        // 2.获取Role中的数据(角色列表显示)
        ActionContextPut(K_ROLE_KEY,roleService.getAllList());

        // 3.判断编辑还是保存
        if ( employee != null && employee.getId() != null ){
            // 编辑
            employee = employeeService.get(employee);
        }else {
            // 保存(为了处理：employee的input.jsp中，显示employee的角色的那个select中，不准许list的key为空)
            employee.setRoles(new HashSet<>());
        }

        return K_INPUT_JSP;
    }

    // 新增|修改的逻辑操作
    @Permission("员工保存|修改")
    public String saveOrUpdate(){

        try {

            if ( employee.getId() == null ){
                // 模拟异常，但是异常不应该在Action中弹出，因为数据库操作成功了没办吧回滚，所以一般都是业务中抛出异常。
//                int y = 1/0;
                // 保存操作
                employeeService.save(employee);
                addActionMessage("保存成功");
            }else {
                // 更新操作
                employeeService.update(employee);
                addActionMessage("更新成功");
            }

        }catch (Exception e){
            addActionError("错误出现");
        }

        return SUCCESS;
    }

    // 删除操作
    @Permission("员工删除")
    public String delete(){
        try {
            employeeService.delete(employee);
            writePlainTextToResponse("删除成功");
        }catch (Exception e){
            writePlainTextToResponse("菜单正在被使用，不可删除!");
        }
        return NONE;
    }

    // 批量删除操作
    @Permission("批量员工删除")
    public String batchDelete(){
        employeeService.batchDelete(this.getIds());
        addActionMessage("删除成功");
        return NONE;
    }

    // 在saveOrUpdate操作前调用
    public void prepareSaveOrUpdate(){
        if (employee != null && employee.getId() != null){
            employee = employeeService.get(employee);
            employee.setDepart(null);
        }
        // 必须增加：否则有两个bug：
        // 1.会有两次关联关系的重复数据(虽然这个使用了domain中equal和hasCode对比id去重解决，但是如果这里删除就不需要那么麻烦了)
        // 2.前端select从左到右侧移动有bug，左边移动到右侧再保存，理应把移动过去的删除，但是没有删除。因为两次赋值给覆盖了。
        employee.getRoles().clear();
    }



    // ------- 故意写的，测试struts的通用异常错误
    public String tyException(){
        int nb = 1/0;
        return SUCCESS;
    }

    // ------- 故意写的，测试struts的通用异常错误
    public String executeToException(){

        try {

            // 获取Department中的数据(部门列表显示)
            ActionContextPut(K_DEPARTMENT_KEY,departmentService.getAllList());

            // 获取数据库查询后的employees列表(员工列表显示)
            ActionContextPut(K_PAGE_RESULT_KEY,employeeService.getQuery(query));

            int nb = 1/0;

        }catch (Exception e){
            addActionError("错误信息");
        }

        return K_LIST_JSP;
    }

}







































