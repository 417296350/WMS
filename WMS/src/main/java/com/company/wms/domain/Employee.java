package com.company.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * Created by xd on 2018/5/4.
 */
@Setter @Getter
public class Employee extends BaseDomain {

    // 1.基本属性
    private String name;         //员工姓名
    private String age;          //员工年龄
    private String email;        //员工邮箱
    private String password;     //员工密码
    private Boolean admin;       //超级管理员

    // 2.关联属性
    // 多对一
    private Department depart;   //部门属性(单向)
    // 多对多
    private Set<Role> roles;           //角色(单向)


    // 3.属性方法
    public String getAllRoleName(){
        try {
            return super.getNameStrFromObjects(roles);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", admin=" + admin +
                '}';
    }


}
