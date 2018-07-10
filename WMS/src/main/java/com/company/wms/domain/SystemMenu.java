package com.company.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xd on 2018/6/20.
 */
@Setter @Getter
public class SystemMenu extends BaseDomain{

    // 1.基本属性
    private String name;    //系统菜单名称
    private String sn;      //系统菜单的编码
    private String url;     //系统菜单对应的URL

    // 2.关联属性
    private SystemMenu parentMenu;  //当前系统菜单的父菜单(多对一聚合关联关系)

    public Map<String,Object> toJson(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",this.getId());
        map.put("pId",this.getParentMenu().getId());
        map.put("name",this.getName());
        map.put("sn",this.getSn());
        map.put("action",this.getUrl());
        return map;
    }
}
