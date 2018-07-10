package com.company.wms.exception;

/**
 * Created by xd on 2018/5/5.
 */
public class NullParameterException extends RuntimeException{

    public NullParameterException(){
        super("参数异常");
    }

    public NullParameterException(String message){
        super(message);
    }

}
