package com.company.wms.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by xd on 2018/6/22.
 */
@Setter @Getter
public class SystemMenuVO {

    private Long id;
    private String name;

    @Override
    public String toString() {
        return "SystemMenuVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public SystemMenuVO(){}

    public SystemMenuVO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
