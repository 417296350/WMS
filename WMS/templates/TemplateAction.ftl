package ${basePkg}.web.action;

import ${basePkg}.annotation.Permission;
import ${basePkg}.domain.${className};
import ${basePkg}.query.impl.${className}Query;
import ${basePkg}.web.service.IPermissionService;
import ${basePkg}.web.service.I${className}Service;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;

/**
 * Created by xd on 2018/5/5.
 */
public class ${className}Action extends BaseAction {

    // ————————业务操作模块
    @Setter
    private I${className}Service ${objectName}Service;

    // ————————接受前端参数
    // 接收前端提交的查询条件
    @Getter
    private ${className}Query query = new ${className}Query();
    // 接受前端提交的${cnName}信息(保存|更新)
    @Getter
    private ${className} ${objectName} = new ${className}();


    // 展示列表
    @Permission("${cnName}列表")
    @InputConfig(methodName = "input")
    public String execute(){
        try {
            ActionContext.getContext().put(K_PAGE_RESULT_KEY,${objectName}Service.getQuery(query));
        }catch (Exception e){
            e.printStackTrace();
            addActionError(e.getMessage());
        }

        return K_LIST_JSP;
    }

    // 进入编辑界面
    @Permission("${cnName}编辑")
    public String input(){

        // 判断是保存还是更新
        if (${objectName} != null && ${objectName}.getId() != null){
            // 更新
            ${objectName} = ${objectName}Service.get(${objectName});
        }else {
        }

        return K_INPUT_JSP;
    }

    // 保存更新操作
    @Permission("${cnName}保存|修改")
    public String saveOrUpdate(){
        try {
            if ( ${objectName}.getId() == null ){
                // 保存操作
                ${objectName}Service.save(${objectName});
                addActionMessage("保存成功");
            }else {
                // 更新操作
                ${objectName}Service.update(${objectName});
                addActionMessage("更新成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }

    // 删除操作
    @Permission("${cnName}删除")
    public String delete(){
        addActionMessage("删除成功");
        ${objectName}Service.delete(${objectName});
        return NONE;
    }

    // 批量删除操作
    @Permission("批量${cnName}删除")
    public String batchDelete(){
        ${objectName}Service.batchDelete(this.getIds());
        addActionMessage("删除成功");
        return NONE;
    }

    // 在saveOrUpdate操作前调用
    public void prepareSaveOrUpdate(){
        if (${objectName} != null && ${objectName}.getId() != null){
            ${objectName} = ${objectName}Service.get(${objectName});
        }
    }
}
