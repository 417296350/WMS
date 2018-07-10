package com.company.wms.web.action;

import com.company.wms.exception.NullParameterException;
import com.company.wms.web.service.ILoginService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Setter;

/**
 * Created by xd on 2018/5/4.
 */
public class LoginAction extends ActionSupport{

    private final String K_MAIN_JSP = "main";

    // ————————业务操作模块
    @Setter
    private ILoginService loginService;

    // ————————接受前端参数
    @Setter
    private String username;
    @Setter
    private String password;



    public String execute(){

        try {
            loginService.Login(username,password);
        }catch (NullParameterException e){
            // 业务方法操作逻辑判断错误，会给抛出异常，此时处理异常错误。
            addActionError(e.getMessage());
            return  Action.LOGIN;
        }

        return K_MAIN_JSP;
    }
}
