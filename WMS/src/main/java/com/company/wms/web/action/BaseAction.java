package com.company.wms.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xd on 2018/5/5.
 */
public class BaseAction extends ActionSupport implements Preparable{

    // result界面对应的字符串
    protected final String K_LIST_JSP  = "list";
    protected final String K_INPUT_JSP = "input";

    // 给前端返回PageResult数据对应的KEY
    protected final String K_PAGE_RESULT_KEY = "pageResult";

    // 批量删除对象的id集合
    @Getter @Setter
    private List<Long> ids = new ArrayList<>();

    // 往上下文ActionContext中添加数据
    protected void ActionContextPut(String key,Object value){
        ActionContext.getContext().put(key, value);
    }

    // 往响应中添加简单文本信息
    protected void writePlainTextToResponse(String text){
        ServletActionContext.getResponse().setContentType("text/plain;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().print(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 往响应中添加JSON文本信息
    protected void writeJSONTextToResponse(String text){
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().print(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void prepare() throws Exception {
    }

}
