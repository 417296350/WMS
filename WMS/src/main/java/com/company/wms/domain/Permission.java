package com.company.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * Created by xd on 2018/5/4.
 */
@Setter @Getter
public class Permission extends BaseDomain {

    // 基本属性
    private String name;    //权限名称
    private String expression; //权限表达式：一个权限对象它的表达式可能对应多个方法表达式，但没有使用集合来表示多个表达式，而是使用了一个字符串，是为了简化操作。如果使用集合，那么分析权限对象和表达式的关系为多对多，此时有设计到了表之间的关联设计。但是如果设计为String，每个表达式方法用逗号分隔开就简化了操作。

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
